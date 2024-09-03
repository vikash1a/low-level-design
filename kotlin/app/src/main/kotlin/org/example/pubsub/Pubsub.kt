package org.example.pubsub

import java.util.concurrent.CopyOnWriteArraySet


data class Message(
    val content: String
)

class Topic(private val name: String, private val subscribers: CopyOnWriteArraySet<Subscriber>){
    fun subscribe(subscriber: Subscriber){
        subscribers.add(subscriber)
    }
    fun unSubscribe(subscriber: Subscriber){
        subscribers.remove(subscriber)
    }
    fun publish(message: Message){
        subscribers.forEach {
            it.onMessage(message)
        }
    }

}

class Publishers(private val topics: MutableSet<Topic>){
    fun addTopic(topic: Topic){
        topics.add(topic)
    }
    fun publish(topic: Topic, message: Message){
        if(topics.contains(topic)){
            topic.publish(message)
        }
    }
}

interface Subscriber{
    fun onMessage(message: Message)
}

class Subscriber1: Subscriber{
    override fun onMessage(message: Message) {
        println("Consumed by subscriber1, message:${message.content}")
    }
}