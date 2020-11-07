package com.onewallet.OneWallet.delivery.rest.imp

import com.onewallet.OneWallet.core.entities.User
import com.onewallet.OneWallet.delivery.rest.api.AccountsResource
import com.onewallet.OneWallet.usecases.UseCaseExecutor
import com.onewallet.OneWallet.usecases.user.RegisterUserByPhoneNumberUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

class AccountResourceImp(
        private val useCaseExecutor: UseCaseExecutor,
        private val registerUserByPhoneNumberUseCase: RegisterUserByPhoneNumberUseCase
) : AccountsResource {

    override fun createAccount(transport: String, phoneNumber: String): CompletionStage<ResponseEntity<Unit>> {

        if (transport != "sms") {
            return CompletableFuture.completedStage(ResponseEntity<Unit>(HttpStatus.UNSUPPORTED_MEDIA_TYPE))
        }
        return useCaseExecutor(
                useCase = registerUserByPhoneNumberUseCase,
                requestDto = phoneNumber,
                requestConverter = { User(UUID.randomUUID(), it) },
                responseConverter = { _ -> ResponseEntity<Unit>(HttpStatus.CREATED) })
    }
}