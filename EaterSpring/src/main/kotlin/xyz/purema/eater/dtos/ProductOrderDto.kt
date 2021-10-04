package xyz.purema.eater.dtos

import java.time.LocalDateTime

data class ProductOrderDto(
    val id: String,
    val createdAt: LocalDateTime,
    val customer: CustomerDto,
    val items: Set<OrderItemDto>
)
