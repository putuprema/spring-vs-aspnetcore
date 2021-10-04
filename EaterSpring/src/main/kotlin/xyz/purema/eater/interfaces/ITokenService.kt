package xyz.purema.eater.interfaces

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import xyz.purema.eater.models.Customer

interface ITokenService {
    fun parse(token: String): Jws<Claims>
    fun generateToken(customer: Customer): String
}