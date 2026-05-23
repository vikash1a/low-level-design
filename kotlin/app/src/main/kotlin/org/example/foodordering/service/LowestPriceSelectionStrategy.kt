package org.example.foodordering.service

import RestaurantService

class LowestPriceSelectionStrategy(
    private  val restaurantService: RestaurantService
): RestaurantSelectionStrategy {
    override fun select(menus: List<String>): List<Pair<String, String>> {
        val menuRestaurants: MutableList<Pair<String, String>> = mutableListOf()
        menus.forEach{
            menuRestaurants.add(Pair(it, restaurantService.getLowestPriceRestaurant(it)))
        }
        return menuRestaurants
    }
}