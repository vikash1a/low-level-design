package org.example.other.foodordering.service

interface RestaurantSelectionStrategy {
    fun select(menus: List<String>): List<Pair<String, String>>
}