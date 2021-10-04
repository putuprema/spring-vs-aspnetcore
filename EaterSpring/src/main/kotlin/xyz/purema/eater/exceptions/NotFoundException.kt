package xyz.purema.eater.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import xyz.purema.eater.dtos.BaseResponseDto

class NotFoundException(message: String) : AppException(message) {
    override fun getResponse(): ResponseEntity<BaseResponseDto> = ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(BaseResponseDto(message ?: "Unknown error"))
}