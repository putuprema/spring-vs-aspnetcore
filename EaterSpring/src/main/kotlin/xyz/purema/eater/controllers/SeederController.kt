package xyz.purema.eater.controllers

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.purema.eater.services.SeederService

@RestController
@RequestMapping("/v1/seeder")
class SeederController(
    private val seederService: SeederService
) {
    @PostMapping("/seed-customers")
    fun seedCustomer() = seederService.seedCustomer()

    @PostMapping("/seed-products")
    fun seedProducts() = seederService.seedProducts()

    @PostMapping("/seed-orders")
    fun seedOrders() = seederService.seedOrder()
}