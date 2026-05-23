package org.example.foodordering

import OrderDao
import OrderService
import RestaurantDao
import RestaurantService
import org.example.foodordering.service.LowestPriceSelectionStrategy
import org.example.foodordering.service.StatsService
import org.junit.jupiter.api.Test

class DriverTest{
    // dao
    private val orderDao = OrderDao()
    private val restaurantDao = RestaurantDao()

    // service
    private val restaurantService = RestaurantService(restaurantDao)
    private val lowestPriceSelectionStrategy = LowestPriceSelectionStrategy(restaurantService)
    private val orderService = OrderService(orderDao,lowestPriceSelectionStrategy,restaurantService)
    private val statsService = StatsService(restaurantService)

    @Test
    fun `order is placed`(){
        restaurantService.add("A2B", listOf(Pair("Idly", 40), Pair("Vada", 30), Pair("Paper Plain Dosa", 50)), 4)
        restaurantService.add("Rasganga", listOf(Pair("Idly", 45), Pair("Set Dosa", 60),  Pair("Poori", 45)), 6)
        restaurantService.add("Eat Fit", listOf(Pair("Idly", 30), Pair("Vada", 40)), 2)

        val orderId1 = orderService.order(listOf("Idly", "Poori"))
        val orderId2 = orderService.order(listOf("Idly", "Vada"))

        statsService.printStats()

        orderService.order(listOf("Idly"))

        orderService.fulfill(orderId1)
        orderService.fulfill(orderId2)

        restaurantService.updateMenu("Eat Fit", listOf(Pair("Idly", 60), Pair("Vada", 40)), 2)

        orderService.order(listOf("Idly"))

    }
}