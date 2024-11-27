package org.example.restaurantmanagement

import java.util.UUID

/*
order(list<menuItem, quantity>)
viewMenu()
reserve(numberOfPeople)

MenuItem(id, name, description, category, price)
Order(id, reservation, list<menuItem, quantity>, totalAmount, paymentStatus, status)
Reservation(id, numberOfPeople)

 */

class RestaurantManagement {
    private val reservations: MutableList<Reservation> = mutableListOf()
    private val orders: MutableList<Order> = mutableListOf()
    private val menuItems: MutableList<MenuItem> = mutableListOf()

    fun reserve(name: String, numberOfPeople: Int){
        reservations.add(Reservation(UUID.randomUUID(),name, numberOfPeople))
    }

    fun order(menuItems: List<Pair<MenuItem,Int>>, reservation: Reservation){
        var totalAmount = 0
        menuItems.forEach{
            totalAmount += it.first.price * it.second
        }
        orders.add(Order(UUID.randomUUID(), reservation,menuItems, totalAmount, OrderStatus.CREATED))
    }

    fun viewMenu(): List<MenuItem>{
        return menuItems
    }

    fun makePayment(totalAmount: Int){
        TODO()
    }
}


// models
data class MenuItem(
    val id: UUID,
    val name: String,
    val description: String,
    val category: Category,
    val price: Int
)

data class Order(
    val id: UUID,
    val reservation: Reservation,
    val menuItems: List<Pair<MenuItem,Int>>,
    val totalAmount: Int,
    val orderStatus: OrderStatus
)

data class Reservation(
    val id: UUID,
    val name: String,
    val numberOfPeople: Int
)

data class Payment(
    val id: UUID,
    val totalAmount: Int,
    val paymentStatus: PaymentStatus
)

enum class PaymentStatus{
    FAILED,
    COMPLETED
}

enum class OrderStatus{
    CREATED,
    IN_PROCESS,
    COMPLETED
}

enum class Category{
    VEG,
    NON_VEG
}
