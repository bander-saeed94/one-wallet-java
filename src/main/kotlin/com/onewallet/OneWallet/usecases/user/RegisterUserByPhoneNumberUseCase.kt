package com.onewallet.OneWallet.usecases.user

import com.onewallet.OneWallet.core.entities.User
import com.onewallet.OneWallet.core.entities.isValid
import com.onewallet.OneWallet.usecases.UseCase
import com.onewallet.OneWallet.usecases.exceptions.ValidationException

class RegisterUserByPhoneNumberUseCase(private val userRepository: UserRepository) : UseCase<User, Unit> {

    override fun execute(request: User) {
        if(!request.isValid()){
            throw ValidationException("invalid phoneNumber")
        }
        userRepository.save(request)
    }

    interface UserRepository {
        /***
         * Throw exception if user already exist with provided number
         */
        fun save(user: User)
    }


}