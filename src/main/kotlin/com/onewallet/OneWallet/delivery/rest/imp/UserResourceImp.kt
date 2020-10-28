package com.onewallet.OneWallet.delivery.rest.imp

import com.onewallet.OneWallet.core.entities.User
import com.onewallet.OneWallet.delivery.rest.api.UsersResource
import com.onewallet.OneWallet.usecases.UseCaseExecutor
import com.onewallet.OneWallet.usecases.user.RegisterUserByPhoneNumberUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.concurrent.CompletionStage

class UserResourceImp(
        private val useCaseExecutor: UseCaseExecutor,
        private val registerUserByPhoneNumberUseCase: RegisterUserByPhoneNumberUseCase
) : UsersResource {
    override fun registerUser(phoneNumber: Double): CompletionStage<ResponseEntity<Unit>> =
            useCaseExecutor(
                    useCase = registerUserByPhoneNumberUseCase,
                    requestDto = phoneNumber,
                    requestConverter = { User(it) },
                    responseConverter = { _ -> ResponseEntity<Unit>(HttpStatus.CREATED) })
}