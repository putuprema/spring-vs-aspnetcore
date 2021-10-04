package xyz.purema.eater.interfaces

import xyz.purema.eater.dtos.ProductOrderDto

interface IProductOrderService {
    fun getById(id: String): ProductOrderDto
    fun getAllOrders(): List<ProductOrderDto>
    fun getByCustomerId(customerId: String): List<ProductOrderDto>
    fun getByProductId(productId: String): List<ProductOrderDto>
}