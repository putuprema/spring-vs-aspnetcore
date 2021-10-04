package xyz.purema.eater.services

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import xyz.purema.eater.interfaces.ITokenService
import xyz.purema.eater.models.Customer
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Service
class TokenService(
    @Value("\${jwt.secret}")
    private val jwtSecret: String,
    @Value("\${jwt.issuer}")
    private val jwtIssuer: String,
    @Value("\${jwt.lifetime}")
    private val jwtLifetime: Long
) : ITokenService {
    private val secretKey =
        SecretKeySpec(jwtSecret.toByteArray(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.jcaName)
    private val parser = Jwts.parserBuilder().setSigningKey(secretKey).build()

    override fun parse(token: String): Jws<Claims> = parser.parseClaimsJws(token)

    override fun generateToken(customer: Customer): String = Jwts.builder()
        .setIssuer(jwtIssuer)
        .setSubject(customer.name)
        .setId(customer.id)
        .setExpiration(Date.from(Instant.now().plus(jwtLifetime, ChronoUnit.SECONDS)))
        .signWith(secretKey)
        .compact()
}