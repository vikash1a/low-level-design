# Design Principles

## SOLID

### S — Single Responsibility Principle
A class should have one reason to change.

```kotlin
// Bad: one class handles both order logic and email
class OrderService {
    fun placeOrder(order: Order) { /* business logic */ }
    fun sendConfirmationEmail(order: Order) { /* email logic */ }
}

// Good: split responsibilities
class OrderService {
    fun placeOrder(order: Order) { /* business logic */ }
}
class EmailService {
    fun sendConfirmation(order: Order) { /* email logic */ }
}
```

> If you have to change the class for two unrelated reasons (business rule changed AND email template changed), SRP is violated.

---

### O — Open/Closed Principle
Open for extension, closed for modification. Add new behaviour by adding new code, not changing existing code.

```kotlin
interface DiscountStrategy {
    fun apply(price: Double): Double
}

class NoDiscount : DiscountStrategy {
    override fun apply(price: Double) = price
}

class SeasonalDiscount : DiscountStrategy {
    override fun apply(price: Double) = price * 0.9
}

// Adding a new discount type never touches existing classes
class LoyaltyDiscount : DiscountStrategy {
    override fun apply(price: Double) = price * 0.85
}
```

> The Strategy pattern is the canonical OCP implementation.

---

### L — Liskov Substitution Principle
A subclass must be substitutable for its base class without breaking the program.

```kotlin
// Violation: Square breaks the contract of Rectangle
class Rectangle {
    open var width: Double = 0.0
    open var height: Double = 0.0
    fun area() = width * height
}

class Square : Rectangle() {
    override var width: Double = 0.0
        set(value) { field = value; height = value }  // side effect — violates LSP
}

// Fix: don't inherit — use a shared interface instead
interface Shape { fun area(): Double }
class Rectangle(val width: Double, val height: Double) : Shape { override fun area() = width * height }
class Square(val side: Double) : Shape { override fun area() = side * side }
```

> LSP check: can you replace every occurrence of the base class with the subclass and have all tests still pass?

---

### I — Interface Segregation Principle
No client should be forced to depend on methods it doesn't use. Prefer many focused interfaces over one fat interface.

```kotlin
// Bad: Robot is forced to implement eat()
interface Worker {
    fun work()
    fun eat()
}

// Good: split by capability
interface Workable { fun work() }
interface Eatable  { fun eat() }

class HumanWorker : Workable, Eatable {
    override fun work() {}
    override fun eat() {}
}

class RobotWorker : Workable {
    override fun work() {}
}
```

---

### D — Dependency Inversion Principle
High-level modules should not depend on low-level modules. Both should depend on abstractions.

```kotlin
// Bad: OrderService is tightly coupled to MySQLDatabase
class OrderService {
    private val db = MySQLDatabase()  // concrete dependency
    fun save(order: Order) = db.save(order)
}

// Good: depend on an interface, inject the implementation
interface OrderRepository {
    fun save(order: Order)
}

class OrderService(private val repo: OrderRepository) {
    fun save(order: Order) = repo.save(order)
}

class MySQLOrderRepository : OrderRepository {
    override fun save(order: Order) { /* MySQL logic */ }
}
```

> DIP enables testability — in tests, inject a fake/mock `OrderRepository` instead of a real DB.

---

## Other Principles

### DRY — Don't Repeat Yourself
Every piece of knowledge should have a single authoritative representation. Duplication means two places to update when logic changes.

### KISS — Keep It Simple, Stupid
Prefer the simplest solution that works. Complexity should be introduced only when the problem demands it.

### YAGNI — You Aren't Gonna Need It
Don't build features for hypothetical future requirements. Build what is needed now; add more when the need is real.

### Composition Over Inheritance
Favour building behaviour by composing objects rather than extending class hierarchies.

```kotlin
// Instead of: class FlyingFish : Fish(), Bird()  (multiple inheritance — not possible)
class Fish(private val swimmer: Swimmer, private val flyer: Flyer) {
    fun swim() = swimmer.swim()
    fun fly()  = flyer.fly()
}
```

> Use inheritance for "is-a". Use composition for "can-do" / behaviour mixing.
