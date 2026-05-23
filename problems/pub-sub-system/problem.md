# Designing a Pub-Sub System in Java

## Requirements
1. The Pub-Sub system should allow publishers to publish messages to specific topics.
2. Subscribers should be able to subscribe to topics of interest and receive messages published to those topics.
3. The system should support multiple publishers and subscribers.
4. Messages should be delivered to all subscribers of a topic in real-time.
5. The system should handle concurrent access and ensure thread safety.
6. The Pub-Sub system should be scalable and efficient in terms of message delivery.

### Design Patterns 
- Observer 

## UML

### self
 
```mermaid
classDiagram

    class Topic{
        name
        publishers
        subscribers

        addPublisher()
        addSubscriber()
    }

    class Publisher{
        name
        publish(topic)
    }
   
    class PublisherImpl1{
        name
    }
    class PublisherImpl2{
        name
    }

    class Subscriber{
        name
        notify()
    }

    class SubscriberImnpl1{
        name
        notify()
    }
    class SubscriberImnpl2{
        name
        notify()
    }

     <<abstract>> Publisher
     PublisherImpl1 --|> Publisher
     PublisherImpl2 --|> Publisher
     <<abstract>> Subscriber
     SubscriberImnpl1 --|> Subscriber
     SubscriberImnpl2 --|> Subscriber
    
```

### copied - [src](https://github.com/ashishps1/awesome-low-level-design/blob/main/problems/pub-sub-system.md)
```mermaid
classDiagram
    class Message{
        content: String
    }
    class Topic{
        name: String
        subscribers: List~Subscriber~

        subscribe(subscriber)
        publish(message)
    }

    class Publisher{
        topics: CopyOnWriteArraySet

        registerTopics(topics)
        publish(topic, messsage)
    }

    class Subscriber{
        onMessage(message)
    }

    class SubscriberImnpl1{
        name: String
        override onMessage(message)
    }
    class SubscriberImnpl2{
        name: String
        override onMessage(message)
    }

     <<abstract>> Subscriber
     SubscriberImnpl1 --|> Subscriber
     SubscriberImnpl2 --|> Subscriber
```

TODO - Handle Concurrency 