package com.ifreedomer.mockk.login.text

object MineTextUtil {
    //    JVMStatic是必须的，否则报错
    @JvmStatic
    fun isEmpty(str: String): Boolean {
        return str.isEmpty()
    }
}