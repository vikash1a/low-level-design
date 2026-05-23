package org.example.designpattern.structural

interface INotifier {
    fun notify()
}

class NotifierImpl : INotifier {
    override fun notify() = println("Notifying")
}

open class BaseDecorator(open val notifier: INotifier) {
    open fun notifyModified() = println("Notifying from base decorator")
}

class FacebookDecorator(override val notifier: INotifier) : BaseDecorator(notifier) {
    override fun notifyModified() {
        notifier.notify()
        println("Notifying from Facebook decorator")
    }
}

class EmailDecorator(override val notifier: INotifier) : BaseDecorator(notifier) {
    override fun notifyModified() {
        notifier.notify()
        println("Notifying from Email decorator")
    }
}
