package xyz.purema.eater.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.purema.eater.dtos.ProductOrderDto
import xyz.purema.eater.dtos.query.ProductOrderQuery
import xyz.purema.eater.interfaces.IProductOrderService

@RestController
@RequestMapping("/v1/orders")
class ProductOrderController(
    private val productOrderService: IProductOrderService
) {
    @GetMapping
    fun getOrders(query: ProductOrderQuery): List<ProductOrderDto> = when {
        query.productId?.isNotEmpty() == true -> productOrderService.getByProductId(query.productId!!)
        query.customerId?.isNotEmpty() == true -> productOrderService.getByCustomerId(query.customerId!!)
        else -> productOrderService.getAllOrders()
    }

    @GetMapping("{id}")
    fun getOrderById(@PathVariable id: String): ProductOrderDto = productOrderService.getById(id)
}