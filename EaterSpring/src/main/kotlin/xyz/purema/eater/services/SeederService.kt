package xyz.purema.eater.services

import com.github.javafaker.Faker
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import xyz.purema.eater.interfaces.ISeederService
import xyz.purema.eater.models.Customer
import xyz.purema.eater.models.Product
import xyz.purema.eater.models.ProductOrder
import xyz.purema.eater.repositories.ICustomerRepository
import xyz.purema.eater.repositories.IProductOrderRepository
import xyz.purema.eater.repositories.IProductRepository
import kotlin.random.Random

@Service
class SeederService(
    private val customerRepository: ICustomerRepository,
    private val productRepository: IProductRepository,
    private val productOrderRepository: IProductOrderRepository,
    private val passwordEncoder: PasswordEncoder
) : ISeederService {
    private val faker = Faker()

    override fun seedCustomer() {
        val customers = mutableListOf<Customer>()
        for (i in 1..1000) {
            val nameFaker = faker.name()
            customers.add(
                Customer(
                    id = nameFaker.username(),
                    name = nameFaker.name(),
                    password = passwordEncoder.encode("123456")
                )
            )
        }
        customerRepository.saveAll(customers)
    }

    override fun seedProducts() {
        val products = mutableListOf<Product>()
        for (i in 1..1000) {
            products.add(Product(faker.food().dish(), faker.number().numberBetween(50000, 500000).toLong()))
        }
        productRepository.saveAll(products)
    }

    override fun seedOrder() {
        val customers = customerRepository.findAll()
        val products = productRepository.findAll()

        val productOrders = mutableListOf<ProductOrder>()

        for (i in 1..2000) {
            val customer = customers.random()
            val productOrder = ProductOrder(customer)

            for (j in 1..3) {
                productOrder.addItem(products.random(), Random.nextInt(1, 5))
            }

            productOrders.add(productOrder)
        }

        productOrderRepository.saveAll(productOrders)
    }
}