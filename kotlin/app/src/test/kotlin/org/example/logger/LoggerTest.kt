package org.example.logger

import kotlin.test.Test

class LoggerTest {
    @Test
    fun test(){
        val loggerConfig = LoggerConfig(
            LogLevel.INFO,
            listOf(ConsoleListener(), FileListener())
        )
        val loggerImpl = LoggerImpl.getInstance(loggerConfig)

        loggerImpl.info(Message("This is a info message"))
        loggerImpl.warn(Message("This is a warn message"))
        loggerImpl.error(Message("This is a error message"))
    }
}