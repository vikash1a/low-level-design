import java.util.UUID

class RestaurantDao {
    private val restaurants: MutableMap<String, Restaurant> = mutableMapOf()

    fun add(name: String, menus: List<Pair<String, Int>>, capacity: Int){
        restaurants[name] = Restaurant(UUID.randomUUID(), name, capacity, menus)
    }

    fun updateMenu(restaurantName: String, menus: List<Pair<String, Int>>, capacity: Int){
        restaurants[restaurantName]!!.menus = menus
        restaurants[restaurantName]!!.capacity = capacity
    }

    fun updateCapacity(restaurantName: String, capacity: Int){
        restaurants[restaurantName]!!.capacity += capacity;
    }

    fun getLowestPriceRestaurant(menuName: String): String{
        var restaurantName: String = ""
        var price: Int = 100000
        restaurants.forEach {
            val name = it.value.name
            if(it.component2().capacity !=0 ){
                it.component2().menus.forEach {
                    if(it.second < price && it.first == menuName){
                        price = it.second
                        restaurantName = name
                    }
                }
            }
        }
        return restaurantName
    }
    fun get(restaurantName: String) = restaurants[restaurantName]!!

    fun getStats(): List<Pair<String, Int>>{
        val stats:MutableList<Pair<String, Int>> = mutableListOf()
        restaurants.forEach {
            stats.add(Pair(it.value.name, it.value.capacity))
        }
        return  stats
    }
}