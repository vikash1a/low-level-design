import java.util.UUID

class OrderDao {
    private val orders: MutableMap<UUID, Order> = mutableMapOf()
    fun add(menus: List<Pair<String, String>>): UUID{
        val id = UUID.randomUUID()
        orders[id] = Order(id, menus, OrderStatus.PLACED)
        return id
    }
    fun updateCompleted(id: UUID){
        orders[id]!!.status = OrderStatus.COMPLETED
    }
    fun getRestaurants(id:UUID): List<String>{
        val restaurants: MutableSet<String> = mutableSetOf()
        orders[id]!!.items.forEach{
            restaurants.add(it.second)
        }
        return restaurants.toList()
    }
}