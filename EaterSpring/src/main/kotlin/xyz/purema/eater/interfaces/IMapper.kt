package xyz.purema.eater.interfaces

import org.mapstruct.Mapper
import xyz.purema.eater.dtos.CustomerDto
import xyz.purema.eater.dtos.OrderItemDto
import xyz.purema.eater.dtos.ProductDto
import xyz.purema.eater.dtos.ProductOrderDto
import xyz.purema.eater.models.Customer
import xyz.purema.eater.models.OrderItem
import xyz.purema.eater.models.Product
import xyz.purema.eater.models.ProductOrder

@Mapper(componentModel = "spring")
interface IMapper {
    fun mapCustomer(customer: Customer): CustomerDto
    fun mapProduct(product: Product): ProductDto
    fun mapProductOrder(productOrder: ProductOrder): ProductOrderDto
    fun mapOrderItem(orderItem: OrderItem): OrderItemDto
    fun mapProductOrders(productOrders: List<ProductOrder>): List<ProductOrderDto>
}