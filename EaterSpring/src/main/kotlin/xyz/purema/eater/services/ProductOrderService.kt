package xyz.purema.eater.services

import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import xyz.purema.eater.dtos.ProductOrderDto
import xyz.purema.eater.exceptions.NotFoundException
import xyz.purema.eater.interfaces.IMapper
import xyz.purema.eater.interfaces.IProductOrderService
import xyz.purema.eater.repositories.ICustomerRepository
import xyz.purema.eater.repositories.IProductOrderRepository

@Service
class ProductOrderService(
    private val customerRepository: ICustomerRepository,
    private val productOrderRepository: IProductOrderRepository,
    private val mapper: IMapper
) : IProductOrderService {
    override fun getById(id: String): ProductOrderDto {
        val productOrder = productOrderRepository.findById(id).orElseThrow {
            NotFoundException("Order not found")
        }
        return mapper.mapProductOrder(productOrder)
    }

    override fun getAllOrders(): List<ProductOrderDto> {
        val orders = productOrderRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
        return mapper.mapProductOrders(orders)
    }

    override fun getByCustomerId(customerId: String): List<ProductOrderDto> {
        val customer = customerRepository.findById(customerId).orElseThrow {
            NotFoundException("Customer not found")
        }
        val orders = productOrderRepository.findAllByCustomer(customer)
        return mapper.mapProductOrders(orders)
    }

    override fun getByProductId(productId: String): List<ProductOrderDto> {
        val orders = productOrderRepository.findAllByProductId(productId)
        return mapper.mapProductOrders(orders)
    }
}