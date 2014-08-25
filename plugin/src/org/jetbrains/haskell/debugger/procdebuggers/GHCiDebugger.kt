package org.jetbrains.haskell.debugger.procdebuggers

import org.jetbrains.haskell.debugger.protocol.HiddenCommand
import org.jetbrains.haskell.debugger.protocol.CommandCallback
import org.jetbrains.haskell.debugger.protocol.ExpressionTypeCommand
import com.intellij.xdebugger.evaluation.XDebuggerEvaluator
import org.jetbrains.haskell.debugger.parser.ExpressionType
import org.jetbrains.haskell.debugger.protocol.ShowExpressionCommand
import org.jetbrains.haskell.debugger.protocol.PrintCommand
import org.jetbrains.haskell.debugger.parser.LocalBinding
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.Condition
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.ui.ConsoleView
import org.jetbrains.haskell.debugger.procdebuggers.utils.DebugRespondent

/**
 * Created by vlad on 7/11/14.
 */

public class GHCiDebugger(debugRespondent: DebugRespondent,
                          debugProcessHandler: ProcessHandler,
                          consoleView: ConsoleView?, val INPUT_READINESS_PORT: Int)
: SimpleDebuggerImpl(debugRespondent, debugProcessHandler, consoleView) {

    class object {
        private val HANDLE_NAME = "__debug_handle"
        private val TRACE_COMMAND_APPENDIX = " >> (withSocketsDo $ $HANDLE_NAME >>= \\ h -> hPutChar h (chr 1) >> hClose h)"
        public val PROMPT_LINE: String = "debug> "
    }

    override val GLOBAL_BREAKPOINT_INDICES: Boolean = true

    override fun fixTraceCommand(line: String): String = "($line)$TRACE_COMMAND_APPENDIX"

    override fun evaluateExpression(expression: String, callback: XDebuggerEvaluator.XEvaluationCallback) {
        val wrapperCallback = object : CommandCallback<ExpressionType?>() {
            override fun execAfterParsing(result: ExpressionType?) {
                if (result == null) {
                    callback.errorOccurred("Unknown expression")
                } else {
                    val showCallback = ShowExpressionCommand.StandardShowExpressionCallback(result.expressionType, callback)
                    enqueueCommand(ShowExpressionCommand(expression, showCallback))
                }
            }
        }
        enqueueCommand(ExpressionTypeCommand(expression, wrapperCallback))
    }

    override fun updateBinding(binding: LocalBinding, lock: Lock, condition: Condition) {
        lock.lock()
        val callback = object : CommandCallback<ExpressionType?>() {
            override fun execAfterParsing(result: ExpressionType?) {
                lock.lock()
                binding.typeName = result?.expressionType
                val printCallback = object : CommandCallback<LocalBinding?>() {
                    override fun execAfterParsing(result: LocalBinding?) {
                        lock.lock()
                        binding.value = result?.value
                        condition.signalAll()
                        lock.unlock()
                    }
                }
                enqueueCommand(PrintCommand(binding.name!!, printCallback))
                lock.unlock()
            }
        }
        enqueueCommand(ExpressionTypeCommand(binding.name!!, callback))
        lock.unlock()
    }

    override fun prepareDebugger() {
        execute(HiddenCommand.createInstance(":set prompt \"$PROMPT_LINE\"\n"))

        val connectToHostPort = "\\host port_ -> let port = toEnum port_ in " +
                                "socket AF_INET Stream 0 >>= " +
                                "(\\sock -> liftM hostAddresses (getHostByName host) >>= " +
                                "(\\addrs -> connect sock (SockAddrInet port (head addrs)) >> " +
                                "socketToHandle sock ReadWriteMode >>=  " +
                                "(\\handle -> return handle)))"
        val host = "\"localhost\""
        var stopCmd = "withSocketsDo $ $HANDLE_NAME >>= \\ h -> hPutChar h (chr 0) >> hClose h"

        /*
         * todo:
         * 1. need to be careful with concurrency of modules
         * 2. handle name can be used
         */
        val commands = array(
                ":m +System.IO\n",
                ":m +Data.Char\n",
                ":m +Network.Socket\n",
                ":m +Network.BSD\n",
                ":m +Control.Monad\n",
                ":m +Control.Concurrent\n",
                "let $HANDLE_NAME = ($connectToHostPort) $host $INPUT_READINESS_PORT\n",
                ":set stop $stopCmd\n")
        commands map { enqueueCommandWithPriority(HiddenCommand.createInstance(it)) }
    }
}