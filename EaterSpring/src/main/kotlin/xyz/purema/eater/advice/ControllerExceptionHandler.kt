package xyz.purema.eater.advice

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import xyz.purema.eater.dtos.BaseResponseDto
import xyz.purema.eater.exceptions.AppException

@RestControllerAdvice
class ControllerExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(AppException::class)
    fun handleAppException(ex: AppException, handlerMethod: HandlerMethod): ResponseEntity<BaseResponseDto> {
        return ex.getResponse()
    }
}