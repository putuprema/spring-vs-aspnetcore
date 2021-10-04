package xyz.purema.eater.models

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Customer(
    @Id
    val id: String,
    var name: String,
    var password: String
) {
    @OneToMany(mappedBy = "customer")
    var productOrders: List<ProductOrder> = emptyList()
}