package xyz.purema.eater.dtos

data class OrderItemDto(
    val id: String,
    val productId: String,
    val name: String,
    val price: Long,
    val qty: Int
)
