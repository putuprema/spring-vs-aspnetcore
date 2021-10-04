package xyz.purema.eater.dtos.form

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotEmpty

class LoginForm {
    @field:NotEmpty(message = "Please enter user id")
    var userId: String? = null

    @field:NotEmpty(message = "Please enter password")
    @field:Length(min = 6, message = "Password must have minimum of 6 characters")
    var password: String? = null
}