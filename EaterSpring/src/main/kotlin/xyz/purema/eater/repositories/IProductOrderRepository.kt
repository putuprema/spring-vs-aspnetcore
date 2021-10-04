package xyz.purema.eater.repositories

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import xyz.purema.eater.models.Customer
import xyz.purema.eater.models.ProductOrder
import java.util.*

interface IProductOrderRepository : JpaRepository<ProductOrder, String> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "product-order-graph")
    override fun findById(id: String): Optional<ProductOrder>

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "product-order-graph")
    override fun findAll(sort: Sort): List<ProductOrder>

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "product-order-graph")
    fun findAllByCustomer(customer: Customer): List<ProductOrder>

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "product-order-graph")
    @Query("SELECT ord FROM ProductOrder ord JOIN OrderItem item ON item.productOrder = ord WHERE item.productId = :productId")
    fun findAllByProductId(productId: String): List<ProductOrder>
}