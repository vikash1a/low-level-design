import java.util.*

data class Order(
    val id: UUID,
    val items: List<Pair<String, String>>,
    var status: OrderStatus
)

enum class OrderStatus {
    PLACED,
    COMPLETED
}