package xyz.purema.eater.controllers

import org.springframework.web.bind.annotation.*
import xyz.purema.eater.dtos.form.LoginForm
import xyz.purema.eater.dtos.form.RegisterForm
import xyz.purema.eater.services.CustomerService
import javax.validation.Valid

@RestController
@RequestMapping("/v1/customer")
class CustomerController(
    private val customerService: CustomerService
) {
    @PostMapping("/register")
    fun register(@Valid @RequestBody form: RegisterForm) = customerService.register(form)

    @PostMapping("/login")
    fun login(@Valid @RequestBody form: LoginForm) = customerService.login(form)

    @GetMapping
    fun getProfile() = customerService.getProfile()
}