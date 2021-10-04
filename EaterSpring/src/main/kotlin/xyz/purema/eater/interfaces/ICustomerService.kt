package xyz.purema.eater.interfaces

import xyz.purema.eater.dtos.AuthResultDto
import xyz.purema.eater.dtos.CustomerDto
import xyz.purema.eater.dtos.form.LoginForm
import xyz.purema.eater.dtos.form.RegisterForm

interface ICustomerService {
    fun register(form: RegisterForm): AuthResultDto
    fun login(form: LoginForm): AuthResultDto
    fun getProfile(): CustomerDto
}