package xyz.purema.eater.exceptions

import org.springframework.http.ResponseEntity
import xyz.purema.eater.dtos.BaseResponseDto

abstract class AppException(message: String) : RuntimeException(message) {
    abstract fun getResponse(): ResponseEntity<BaseResponseDto>
}