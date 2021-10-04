package xyz.purema.eater.models

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class OrderItem(
    @ManyToOne
    val productOrder: ProductOrder,
    val productId: String,
    val name: String,
    val price: Long,
    val qty: Int
) {
    @Id
    val id: String = UUID.randomUUID().toString()
}