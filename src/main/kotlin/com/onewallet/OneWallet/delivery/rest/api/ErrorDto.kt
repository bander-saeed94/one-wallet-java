package com.onewallet.OneWallet.delivery.rest.api

data class ErrorDto(
        val errorCode: ErrorCodeDto?,
        val message: String?
)

enum class ErrorCodeDto {
    NOT_FOUND,
    VALIDATION_ERROR
}