package com.onewallet.OneWallet.delivery.rest.imp

import com.onewallet.OneWallet.core.entities.User
import com.onewallet.OneWallet.core.entities.VerifyUser
import com.onewallet.OneWallet.delivery.rest.api.AccountsResource
import com.onewallet.OneWallet.delivery.rest.api.VerifyUserBody
import com.onewallet.OneWallet.usecases.UseCaseExecutor
import com.onewallet.OneWallet.usecases.user.RegisterUserByPhoneNumberUseCase
import com.onewallet.OneWallet.usecases.user.VerifyUserUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

class AccountResourceImp(
        private val useCaseExecutor: UseCaseExecutor,
        private val registerUserByPhoneNumberUseCase: RegisterUserByPhoneNumberUseCase,
        private val verifyUserUseCase: VerifyUserUseCase
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

    override fun verifyAccount(verificationCode: String, authorizationHeader: String, body: VerifyUserBody): CompletionStage<ResponseEntity<Unit>> {

        val header = authorizationHeader.split(" ")
        if(header[0] != "Basic"){
            println("unsupported authentication method")
            return CompletableFuture.completedStage(ResponseEntity<Unit>(HttpStatus.UNAUTHORIZED))
        }
        val basicAuth = header[1]
        val phoneNumber = String(Base64.getDecoder().decode(basicAuth)).split(":")[0]
        val password = String(Base64.getDecoder().decode(basicAuth)).split(":")[1]

        return useCaseExecutor(
                useCase = verifyUserUseCase,
                requestDto = Unit,
                requestConverter = { VerifyUser(verificationCode, phoneNumber, password, body.registrationId) },
                responseConverter = { _ -> ResponseEntity<Unit>(HttpStatus.ACCEPTED) })
    }
}