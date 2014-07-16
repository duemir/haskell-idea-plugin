package org.jetbrains.haskell.debugger.commands

/**
 * Created by vlad on 7/16/14.
 */

public class ResumeCommand : AbstractCommand() {

    override fun getBytes(): ByteArray {
        return ":continue\n".toByteArray()
    }

}
