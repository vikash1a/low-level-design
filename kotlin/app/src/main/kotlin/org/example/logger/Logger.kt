package org.example.logger

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

// logger
interface Logger{
    fun log(logLevel: LogLevel, message: Message)
    fun info(message: Message)
    fun error(message: Message)
    fun warn(message: Message)
}

class LoggerImpl private constructor(private val loggerConfig: LoggerConfig): Logger{

    override fun log(logLevel: LogLevel, message: Message) {
        message.logLevel = logLevel
        if(logLevel.ordinal >= loggerConfig.logLevel.ordinal){
            loggerConfig.listeners.forEach{
                it.update(message)
            }
        }
    }

    override fun info(message: Message) {
        log(LogLevel.INFO, message)
    }

    override fun error(message: Message) {
        log(LogLevel.ERROR, message)
    }

    override fun warn(message: Message) {
        log(LogLevel.WARN, message)
    }

    companion object{
        private var loggerImpl: LoggerImpl? = null
        fun getInstance(loggerConfig: LoggerConfig): Logger{
            return loggerImpl?: synchronized(this){
                loggerImpl?: LoggerImpl(loggerConfig).also { loggerImpl = it }
            }
        }
    }

}

data class LoggerConfig(
    val logLevel: LogLevel,
    val listeners: List<Listener>
)

//listener
interface Listener{
    fun update(message: Message)
}
class ConsoleListener: Listener{
    override fun update(message: Message) {
        println("Log received in console listener: $message")
    }
}
class FileListener: Listener{
    override fun update(message: Message) {
        println("Log received in file listener: $message")
    }
}

// Model
data class Message(
    val message: String,
    var logLevel: LogLevel = LogLevel.INFO,
    val timestamp: LocalDateTime = LocalDateTime.now()
){
    override fun toString(): String{
        return "$logLevel $timestamp $message"
    }
}

enum class LogLevel{
    INFO,
    WARN,
    ERROR
}