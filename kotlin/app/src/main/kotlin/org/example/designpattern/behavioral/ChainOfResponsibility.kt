package org.example.designpattern.behavioral

interface IHandler {
    var nextHandler: IHandler?
    fun handleRequest(request: String)
}

abstract class BaseHandler : IHandler {
    override var nextHandler: IHandler? = null
    fun setNext(handler: IHandler): IHandler {
        nextHandler = handler
        return handler
    }
}

class DebugHandler : BaseHandler() {
    override fun handleRequest(request: String) {
        if (request == "DEBUG") println("DebugHandler handled: $request")
        else nextHandler?.handleRequest(request)
    }
}

class InfoHandler : BaseHandler() {
    override fun handleRequest(request: String) {
        if (request == "INFO") println("InfoHandler handled: $request")
        else nextHandler?.handleRequest(request)
    }
}

class ErrorHandler : BaseHandler() {
    override fun handleRequest(request: String) {
        if (request == "ERROR") println("ErrorHandler handled: $request")
        else println("Unhandled request: $request")
    }
}
