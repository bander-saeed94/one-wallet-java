package com.onewallet.OneWallet.usecases.user

import com.onewallet.OneWallet.core.entities.Otp
import com.onewallet.OneWallet.core.entities.User
import com.onewallet.OneWallet.core.entities.isValid
import com.onewallet.OneWallet.usecases.UseCase
import com.onewallet.OneWallet.usecases.exceptions.BusinessException
import com.onewallet.OneWallet.usecases.exceptions.ValidationException
import com.onewallet.OneWallet.usecases.gateways.util.OtpUtil

class RegisterUserByPhoneNumberUseCase(
        private val userRepository: UserRepository,
        private val otpRepository: OtpRepository,
        private val smsSender: SmsSender,
        private val otpUtil: OtpUtil
) : UseCase<User, Unit> {

    override fun execute(request: User) {
        if (!request.isValid()) {
            throw ValidationException("invalid phoneNumber")
        }
        val user = userRepository.findByPhoneNumber(request.phoneNumber)
        val userExists = user!= null
//        val userExistsAndVerified = userExists && user!!.verifiedByPhoneNumber
//        if (userExistsAndVerified) {
//            throw BusinessException("user is already verified")
//        }

        try {
            val otp = otpUtil.generate(60, "SHA1", 4)

            smsSender.sendOtp(request.phoneNumber, otp.token)
            otpRepository.save(otp, request.phoneNumber)
            if(!userExists){
                userRepository.save(request)
            }
        } catch (e: Exception) {
            throw BusinessException(e.message ?: "unknown message")
        }
    }

    interface UserRepository {
        fun save(user: User)
        fun findByPhoneNumber(phoneNumber: String): User?
    }

    interface OtpRepository {
        fun save(otp: Otp, phoneNumber: String)
    }

    interface SmsSender {
        fun sendOtp(phoneNumber: String, otp: String)
    }

}