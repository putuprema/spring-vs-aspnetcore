package xyz.purema.eater.models

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@NamedEntityGraph(
    name = "product-order-graph",
    attributeNodes = [
        NamedAttributeNode("items"),
        NamedAttributeNode("customer")
    ]
)
data class ProductOrder(
    @ManyToOne
    val customer: Customer
) {
    @Id
    val id: String = UUID.randomUUID().toString()
    val createdAt: LocalDateTime = LocalDateTime.now()

    @OneToMany(mappedBy = "productOrder", cascade = [CascadeType.ALL])
    var items: MutableSet<OrderItem> = mutableSetOf()

    fun addItem(product: Product, qty: Int) {
        items.add(
            OrderItem(
                productOrder = this,
                productId = product.id,
                name = product.name,
                price = product.price,
                qty = qty
            )
        )
    }
}
