package com.onewallet.OneWallet.delivery.rest.imp

import com.onewallet.OneWallet.delivery.rest.api.ErrorCodeDto
import com.onewallet.OneWallet.delivery.rest.api.ErrorDto
import com.onewallet.OneWallet.usecases.exceptions.BusinessException
import com.onewallet.OneWallet.usecases.exceptions.NotFoundException
import com.onewallet.OneWallet.usecases.exceptions.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
@RestController
private class GlobalExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(NotFoundException::class)
    fun notFound(ex: NotFoundException) =
            ResponseEntity(ErrorDto(ErrorCodeDto.NOT_FOUND, "Resource not found"), HttpStatus.NOT_FOUND)

    @ExceptionHandler(ValidationException::class)
    fun alreadyExists(ex: ValidationException) =
            ResponseEntity(ErrorDto(ErrorCodeDto.VALIDATION_ERROR, ex.message), HttpStatus.BAD_REQUEST)

    @ExceptionHandler(BusinessException::class)
    fun businessError(ex: BusinessException) =
            ResponseEntity(ErrorDto(ErrorCodeDto.BUSINESS_ERROR, ex.message), HttpStatus.BAD_REQUEST)

}