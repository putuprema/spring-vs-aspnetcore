package xyz.purema.eater.exceptions

import org.springframework.security.core.AuthenticationException

class UnauthorizedException(message: String? = "Unauthorized") : AuthenticationException(message)