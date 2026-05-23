# Low-Level Design — Interview Prep

## Reference
- [awesome-low-level-design](https://github.com/ashishps1/awesome-low-level-design/tree/main)
- [low-level-design-primer](https://github.com/prasadgujar/low-level-design-primer)

## Repo Structure

```
theory/                        # OOP, design principles, design patterns
  oops-concept.md
  design-principles.md
  design-patterns/
    behavioral.md              # Chain of Responsibility, Observer, Strategy
    creational.md              # Singleton, Factory, Abstract Factory, Builder
    structural.md              # Adapter, Decorator, Bridge, Composite, Facade

problems/                      # One folder per problem
  <problem-name>/
    problem.md                 # Requirements and class diagram
    practice.md                # Timed self-practice attempts and learnings

kotlin/                        # Gradle project — all implementations
  app/src/main/kotlin/org/example/
    <problem>/                 # Kotlin implementation
  app/src/test/kotlin/org/example/
    <problem>/                 # Tests
```

## How to Solve LLD

1. Clarify requirements (functional vs non-functional)
2. Identify entities (nouns → classes)
3. Sketch class diagram (relationships, key attributes)
4. Define interfaces first, then concrete classes
5. Implement core logic (hardest method first)
6. Apply patterns — name them explicitly
7. Discuss trade-offs and thread safety

## Problems

| #  | Problem                      | Difficulty | Status       |
|----|------------------------------|------------|--------------|
| 1  | Splitwise                    | Hard       | Done         |
| 2  | Logging Framework            | Easy       | Done         |
| 3  | Pub Sub System               | Easy       | Done         |
| 4  | Tic Tac Toe                  | Easy       | Done         |
| 5  | Parking Lot                  | Easy       | Done         |
| 6  | Car Rental                   | Medium     | Done         |
| 7  | Movie Ticket Booking         | Hard       | Done         |
| 8  | Online Shopping — Amazon     | Hard       | Done         |
| 9  | Social Network — Facebook    | Medium     | Done         |
| 10 | Ride Sharing — Uber          | Hard       | Partial      |
| 11 | Snake and Ladder             | Hard       | Done         |
| 12 | Restaurant Management System | Medium     | Partial      |
| 13 | Stock Brokerage System       | Medium     | Done         |
| 14 | ATM                          | Medium     | Done         |
| 15 | Food Ordering System         | Medium     | Done         |
| 16 | Hotel Management             | Medium     | Pending      |
| 17 | LinkedIn                     | Medium     | Pending      |
| 18 | LRU Cache                    | Medium     | Pending      |
