# Pub-Sub System — Practice Notes

## Requirements (from memory)

1. Publishers publish messages to specific topics
2. Subscribers subscribe to topics and receive messages
3. Support multiple publishers and subscribers
4. Messages delivered to all subscribers in real-time
5. Concurrent access — thread-safe
6. Scalable and efficient message delivery

## Solution sketch (first attempt)

```
class Publisher(broker) {
    topic
    publish(message) { broker.produce(message, topic) }
}
class Consumer {
    consume()
}
class Broker {
    topics
    subscribe(topic, consumer)
    produce(message, topic) {
        topic.subscribers.forEach { it.consume(message) }
    }
}
class Topic {
    name
    list<publisher>
    list<consumer>
}
```

## Learnings / corrected model

```
class Topic {
    name
    subscribers
    subscribe()
    unsubscribe()
    publish()
}
class Publisher(topics) {
    produce(message, topic) { topics[topic].publish() }
}
class Subscriber {
    notify()
}
```

- Topic owns the subscriber list and the publish logic (not the Broker)
- Pattern: Observer — Topic is the Subject, Subscriber is the Observer
