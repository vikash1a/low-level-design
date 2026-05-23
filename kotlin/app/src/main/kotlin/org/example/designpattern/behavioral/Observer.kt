package org.example.designpattern.behavioral

interface IObserver {
    fun update(message: String)
}

interface ISubject {
    fun addObserver(observer: IObserver)
    fun removeObserver(observer: IObserver)
    fun notifyObservers(message: String)
}

class ConcreteSubject : ISubject {
    private val observers = mutableListOf<IObserver>()

    override fun addObserver(observer: IObserver) { observers.add(observer) }
    override fun removeObserver(observer: IObserver) { observers.remove(observer) }
    override fun notifyObservers(message: String) { observers.forEach { it.update(message) } }

    fun doWork() {
        println("Subject doing work")
        notifyObservers("work done")
    }
}

class EmailObserver : IObserver {
    override fun update(message: String) = println("Email notified: $message")
}

class SmsObserver : IObserver {
    override fun update(message: String) = println("SMS notified: $message")
}
