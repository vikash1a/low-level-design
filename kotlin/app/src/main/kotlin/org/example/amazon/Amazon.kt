package org.example.amazon

import java.util.UUID

class Amazon(
    private val productService: ProductService,
    private val orderService: OrderService
) {
    fun browse(query: String, category: ProductCategory): List<Product>{
        return productService.browse(query, category)
    }
    fun addToCart(product: Product){
        TODO()
    }
    fun placeOrder(user: User, products: List<Product>){
        val order = Order(UUID.randomUUID().toString(), user, products)
        products.forEach{
            productService.decrementQuantity(it,1)
        }
        orderService.placeOrder(order);
    }
}

// service
class ProductService{
    private val products: MutableList<Product> = mutableListOf()
    fun browse(query: String, category: ProductCategory): List<Product>{
        return products.filter { it.category == category && it.isAvailable() && it.name.contains(query) }
    }
    fun add(product: Product){
        TODO()
    }
    fun decrementQuantity(product: Product,quantity: Int){
        if(product.quantity < quantity)throw Exception("Not Enough Quantity")
        product.quantity -= quantity
    }
    fun incrementQuantity(product: Product,quantity: Int){
        TODO()
    }
    fun remove(product: Product){
        TODO()
    }
}
class OrderService{
    private val orders: MutableList<Order> = mutableListOf()
    fun placeOrder(order: Order){
        orders.add(order)
    }
}
// models
data class Product (
    val id: String,
    val name: String,
    var quantity: Int,
    val category: ProductCategory,
    val price: Int
){
    fun isAvailable() = quantity>=1
}

data class User(
    val id: String,
    val name: String,
)

data class Order(
    val id: String,
    val user: User,
    val products: List<Product>,
    val payment: Payment? = null,
    val amount: Int? = null,
)
data class OnlinePayment(
    override val id: String,
    override val amount: Int,
    override val completed: Boolean,
): Payment

data class CodPayment(
    override val id: String,
    override val amount: Int,
    override val completed: Boolean,
): Payment

interface  Payment {
    val id: String
    val amount: Int
    val completed: Boolean
}
enum class ProductCategory{
    ELECTRONICS,
    BOOKS,
    CLOTHES,
    UNKNOWN
}

enum class OrderStatus{
    CREATED,
    DISPATCHED,
    COMPLETED,
    CANCELLED
}


