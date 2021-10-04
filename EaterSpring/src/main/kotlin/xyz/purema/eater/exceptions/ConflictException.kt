package xyz.purema.eater.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import xyz.purema.eater.dtos.BaseResponseDto

class ConflictException(message: String) : AppException(message) {
    override fun getResponse(): ResponseEntity<BaseResponseDto> = ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(BaseResponseDto(message ?: "Unknown error"))
}