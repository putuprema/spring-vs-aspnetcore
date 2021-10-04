package xyz.purema.eater.models

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Product(
    var name: String,
    var price: Long
) {
    @Id
    val id: String = UUID.randomUUID().toString()
}
