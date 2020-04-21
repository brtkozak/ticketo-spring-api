package com.example.ticketo.controller.exception

import com.example.ticketo.model.ApiError
import com.example.ticketo.model.ApiSubError
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val message = "Malformed JSON request"
        return buildResponseEntity(ApiError(status = HttpStatus.BAD_REQUEST, message = message, debugMessage = ex.localizedMessage))
    }


    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val subErrors = mutableListOf<ApiSubError>()
        ex.bindingResult.allErrors.forEach {
            subErrors.add(ApiSubError(
                    it.objectName,
                    (it as FieldError).field,
                    it.rejectedValue,
                    it.defaultMessage
            ))
        }
        val message = "Data validation error"
        return buildResponseEntity(
                ApiError(
                        status = HttpStatus.NOT_FOUND,
                        message = message,
                        debugMessage = ex.localizedMessage,
                        subErrors = subErrors
                ))
    }

    @ExceptionHandler(SimpleResponseException::class)
    fun handleSimpleException(ex : SimpleResponseException) : ResponseEntity<Any>{
        return buildResponseEntity(
                ApiError(
                        status = ex.status,
                        message = ex.message
                ))
    }

    private fun buildResponseEntity(apiError: ApiError): ResponseEntity<Any> {
        return ResponseEntity(apiError, apiError.status!!)
    }
}