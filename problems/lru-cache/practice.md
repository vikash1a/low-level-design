# LRU Cache — Practice Notes

## Requirements (from memory)

1. `put(key, value)` — insert; evict least recently used if at capacity
2. `get(key)` — return value and promote to most-recently-used; return -1 if absent
3. Fixed capacity set at init
4. Thread-safe
5. O(1) for both put and get

## Solution sketch

```
class LRUCache {
    map<String, Node> mp       // O(1) lookup
    DeQueue<Node> queue        // front = MRU, back = LRU
    capacity: Int

    get(key): String
    put(key, value)
}
```

- Use `LinkedHashMap` in Kotlin/Java — handles both lookup and insertion order in one structure
- Or: `HashMap` + doubly-linked list for manual O(1) control
