package org.jetbrains.haskell.debugger.breakpoints

import com.intellij.xdebugger.breakpoints.XBreakpointProperties

/**
 * Created by vlad on 8/6/14.
 */

public class HaskellExceptionBreakpointProperties(): XBreakpointProperties<HaskellExceptionBreakpointProperties.State>() {

    public enum class ExceptionType {
        EXCEPTION
        ERROR
    }

    public class State {
        public var exceptionType: ExceptionType = HaskellExceptionBreakpointProperties.ExceptionType.EXCEPTION
    }

    private var myState: HaskellExceptionBreakpointProperties.State = HaskellExceptionBreakpointProperties.State()

    override fun getState(): State {
        return myState
    }

    override fun loadState(state: State?) {
        myState = state!!
    }
}