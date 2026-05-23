Model
- Restaurant 
    - (id, name, list<menu(id, name, price, rxId)>, capacity)
    - add
    - updateCapacity
    - update menu
- order (restaurant)
  - (id, list<menu(name)>, totalAmount, status(placed, completed))
  - add
  - fulfill
- Restaurant selection strategy
  - strategy pattern 
- StatsTracker(restaurants)
  - background work

TODO
 - Pair in kotlin 