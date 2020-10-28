package com.onewallet.OneWallet.usecases.user

import com.onewallet.OneWallet.core.entities.User
import com.onewallet.OneWallet.core.entities.isValid
import com.onewallet.OneWallet.usecases.UseCase
import com.onewallet.OneWallet.usecases.exceptions.ValidationException
import java.sql.SQLException

class RegisterUserByPhoneNumberUseCase(private val userRepository: UserRepository) : UseCase<User, Unit> {

    override fun execute(request: User) {
        if(!request.isValid()){
            throw ValidationException("invalid phoneNumber")
        }
        try {
            userRepository.save(request)
        } catch (e: Exception){
            if(e is SQLException){
                println(e)
                throw ValidationException(e.sqlState)
            }
            throw ValidationException(e.message ?: "unknown message")
        }
    }

    interface UserRepository {
        /***
         * Throw exception if user already exist with provided number
         */
        fun save(user: User)
    }


}