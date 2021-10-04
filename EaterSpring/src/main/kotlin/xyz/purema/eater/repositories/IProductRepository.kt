package xyz.purema.eater.repositories

import org.springframework.data.jpa.repository.JpaRepository
import xyz.purema.eater.models.Product

interface IProductRepository : JpaRepository<Product, String> {
    fun findAllByNameContains(name: String): List<Product>
}