package org.example.other.foodordering.dao

import Menu
import java.util.UUID

class MenuDao {
    private val menus: MutableMap<String, Menu> = mutableMapOf()
    fun add(name: String, price: Int){
        val id = UUID.randomUUID()
        menus[name] = Menu(id, name, price)
    }
    fun update(name: String, price: Int){
        val id = UUID.randomUUID()
        menus[name] = Menu(id, name, price)
    }
}