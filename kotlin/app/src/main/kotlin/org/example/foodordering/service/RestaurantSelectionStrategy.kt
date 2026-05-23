package org.example.foodordering.service

interface RestaurantSelectionStrategy {
    fun select(menus: List<String>): List<Pair<String, String>>
}