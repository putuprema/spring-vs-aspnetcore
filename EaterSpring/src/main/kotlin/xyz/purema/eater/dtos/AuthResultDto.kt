package xyz.purema.eater.dtos

data class AuthResultDto(
    val accessToken: String,
    val profile: CustomerDto
)
