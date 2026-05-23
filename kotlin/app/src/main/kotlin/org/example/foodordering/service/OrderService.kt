import org.example.foodordering.service.RestaurantSelectionStrategy
import java.util.UUID

class OrderService(
    private val orderDao: OrderDao,
    private val restaurantSelectionStrategy: RestaurantSelectionStrategy,
    private val restaurantService: RestaurantService
){
    fun order(menus: List<String>): UUID{
        val menuRestaurants = restaurantSelectionStrategy.select(menus)
        val uniqueRestaurants = menuRestaurants.map { it.second }.toSet().toList()
        uniqueRestaurants.forEach {
            restaurantService.updateCapacity(it, -1)
        }
        println("Ordered from $uniqueRestaurants")
        return orderDao.add(menuRestaurants)
    }

    fun fulfill(id: UUID){
        orderDao.updateCompleted(id)
        val restaurants = orderDao.getRestaurants(id)
        restaurants.forEach{
            restaurantService.updateCapacity(it, 1)
        }
    }
}