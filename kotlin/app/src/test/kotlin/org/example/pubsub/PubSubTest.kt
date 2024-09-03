package org.example.pubsub

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.concurrent.CopyOnWriteArraySet

class PubSubTest {

    @Test
    fun `pub-sub without publisher`() {
        val subscriber = Subscriber1()
        val topic  = Topic("test-topic", CopyOnWriteArraySet())
        topic.subscribe(subscriber)

        topic.publish(Message("test-message"))

        topic.unSubscribe(subscriber)
    }

    @Test
    fun `pub-sub with publisher`(){
        val subscriber = Subscriber1()
        val topic  = Topic("test-topic", CopyOnWriteArraySet())
        topic.subscribe(subscriber)
        val publishers = Publishers(mutableSetOf())
        publishers.addTopic(topic)

        publishers.publish(topic, Message("test-message"))
    }
}