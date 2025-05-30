package org.example.other.foodordering.service

import RestaurantService

class StatsService(private val restaurantService: RestaurantService) {

    fun printStats(){
        val stats = restaurantService.getStats()
        stats.forEach {
            println("${it.first} - ${it.second}")
        }
    }
}