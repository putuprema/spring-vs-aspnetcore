package xyz.purema.eater.repositories

import org.springframework.data.jpa.repository.JpaRepository
import xyz.purema.eater.models.Customer

interface ICustomerRepository : JpaRepository<Customer, String>