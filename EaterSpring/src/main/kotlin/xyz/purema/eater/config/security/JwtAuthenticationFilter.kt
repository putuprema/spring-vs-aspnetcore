package xyz.purema.eater.config.security

import io.jsonwebtoken.JwtException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import xyz.purema.eater.interfaces.ITokenService
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
    private val tokenService: ITokenService
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorization = request.getHeader("Authorization")

        if (authorization == null || !authorization.startsWith("Bearer")) {
            return filterChain.doFilter(request, response)
        }

        val token = authorization.replaceFirst("Bearer ", "")

        val claims = try {
            tokenService.parse(token).body
        } catch (ex: JwtException) {
            SecurityContextHolder.clearContext()
            return
        }

        SecurityContextHolder.getContext().authentication =
            UsernamePasswordAuthenticationToken(claims.id, null, arrayListOf())
        filterChain.doFilter(request, response)
    }
}