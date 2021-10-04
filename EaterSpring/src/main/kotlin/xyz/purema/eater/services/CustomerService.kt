package xyz.purema.eater.services

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import xyz.purema.eater.dtos.AuthResultDto
import xyz.purema.eater.dtos.CustomerDto
import xyz.purema.eater.dtos.form.LoginForm
import xyz.purema.eater.dtos.form.RegisterForm
import xyz.purema.eater.exceptions.ConflictException
import xyz.purema.eater.exceptions.NotFoundException
import xyz.purema.eater.exceptions.UnauthorizedException
import xyz.purema.eater.interfaces.ICustomerService
import xyz.purema.eater.interfaces.IMapper
import xyz.purema.eater.interfaces.ITokenService
import xyz.purema.eater.models.Customer
import xyz.purema.eater.repositories.ICustomerRepository

@Service
class CustomerService(
    private val mapper: IMapper,
    private val passwordEncoder: PasswordEncoder,
    private val customerRepository: ICustomerRepository,
    private val tokenService: ITokenService
) : ICustomerService {
    override fun register(form: RegisterForm): AuthResultDto {
        customerRepository.findById(form.userId!!).ifPresent {
            throw ConflictException("User already exists")
        }

        val customer = Customer(form.userId!!, form.name!!, passwordEncoder.encode(form.password!!))
        customerRepository.save(customer)

        return AuthResultDto(
            accessToken = tokenService.generateToken(customer),
            profile = mapper.mapCustomer(customer)
        )
    }

    override fun login(form: LoginForm): AuthResultDto {
        val customer = customerRepository.findById(form.userId!!).orElseThrow {
            UnauthorizedException("Bad Credentials")
        }

        if (!passwordEncoder.matches(form.password!!, customer.password)) {
            throw UnauthorizedException("Bad Credentials")
        }

        return AuthResultDto(
            accessToken = tokenService.generateToken(customer),
            profile = mapper.mapCustomer(customer)
        )
    }

    override fun getProfile(): CustomerDto {
        val userId = SecurityContextHolder.getContext().authentication.principal as String
        val customer = customerRepository.findById(userId).orElseThrow {
            NotFoundException("User not found")
        }

        return mapper.mapCustomer(customer)
    }
}