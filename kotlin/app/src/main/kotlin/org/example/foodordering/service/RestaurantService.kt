class RestaurantService(
    private val restaurantDao: RestaurantDao
) {
    fun add(name: String, menus: List<Pair<String, Int>>, capacity: Int){
        restaurantDao.add(name, menus, capacity)
    }

    fun updateMenu(restaurantName: String, menus: List<Pair<String, Int>>, capacity: Int){
        restaurantDao.updateMenu(restaurantName,menus, capacity)
    }

    fun getLowestPriceRestaurant(menuName: String): String{
        return restaurantDao.getLowestPriceRestaurant(menuName)
    }
    fun get(restaurantName: String) = restaurantDao.get(restaurantName)
    fun updateCapacity(restaurantName: String, capacity: Int){
        restaurantDao.updateCapacity(restaurantName,capacity)
    }
    fun getStats(): List<Pair<String, Int>>{
        return  restaurantDao.getStats()
    }
}