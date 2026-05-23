# OOP Concepts

## Encapsulation

Restrict direct access to internal state. Expose behaviour through a controlled public API.

**Why it matters:** Lets you change the internal implementation without breaking callers.

```kotlin
class BankAccount(initialBalance: Double) {
    private var balance: Double = initialBalance  // hidden

    fun deposit(amount: Double) {
        require(amount > 0)
        balance += amount
    }

    fun getBalance(): Double = balance  // read-only view
}
```

> Interview tip: mention that encapsulation also includes hiding *implementation details*, not just fields — e.g. keeping helper methods private.

---

## Abstraction

Expose *what* an object can do, hide *how* it does it.

**Why it matters:** Consumers depend on a contract (interface/abstract class), not a concrete implementation — making the system easy to extend and test.

```kotlin
interface PaymentGateway {
    fun charge(amount: Double): Boolean
}

class StripeGateway : PaymentGateway {
    override fun charge(amount: Double): Boolean { /* Stripe API call */ return true }
}

class PayPalGateway : PaymentGateway {
    override fun charge(amount: Double): Boolean { /* PayPal API call */ return true }
}
```

The caller holds a `PaymentGateway` reference — it never sees Stripe or PayPal internals.

> Abstraction vs Encapsulation: Abstraction is about *design* (what to expose). Encapsulation is about *implementation* (how to protect state).

---

## Inheritance

A subclass reuses and extends the behaviour of a parent class.

**Two forms:**
- `extends` an abstract class — inherits partial implementation
- `implements` an interface — inherits only the contract

```kotlin
abstract class Shape {
    abstract fun area(): Double
    fun describe() = "I am a shape with area ${area()}"  // reused by subclasses
}

class Circle(val radius: Double) : Shape() {
    override fun area() = Math.PI * radius * radius
}

class Rectangle(val w: Double, val h: Double) : Shape() {
    override fun area() = w * h
}
```

> Prefer composition over inheritance for behaviour sharing. Use inheritance only for true "is-a" relationships.

---

## Polymorphism

The same interface behaves differently depending on the runtime type.

### Runtime polymorphism (method overriding)
```kotlin
val shapes: List<Shape> = listOf(Circle(5.0), Rectangle(3.0, 4.0))
shapes.forEach { println(it.area()) }  // dispatches to the correct override at runtime
```

### Compile-time polymorphism (overloading)
```kotlin
fun log(message: String) { println(message) }
fun log(message: String, level: String) { println("[$level] $message") }
```

> In LLD interviews, runtime polymorphism is what makes Strategy, Observer, and Factory patterns work — always connect the concept to a pattern.

---

## Class Relationships (UML)

| Relationship  | Meaning                                      | Keyword         | Example                              |
|---------------|----------------------------------------------|-----------------|--------------------------------------|
| Association   | A uses B (loose)                             | has-a           | `Order` has a `Customer`             |
| Aggregation   | A owns B, B can exist without A              | has-a (weak)    | `Classroom` has `Students`           |
| Composition   | A owns B, B cannot exist without A           | has-a (strong)  | `House` has `Rooms`                  |
| Inheritance   | A is a B                                     | is-a            | `Circle` is a `Shape`                |
| Realization   | A implements interface B                     | implements      | `StripeGateway` implements `Payment` |
| Dependency    | A temporarily uses B (method param/local)    | uses            | `OrderService` uses `EmailSender`    |
