import java.util.UUID

data class Restaurant(
    val id: UUID,
    val name: String,
    var capacity: Int,
    var menus: List<Pair<String,Int>>
)