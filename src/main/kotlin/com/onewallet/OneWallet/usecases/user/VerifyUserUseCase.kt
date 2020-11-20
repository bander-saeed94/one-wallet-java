package com.onewallet.OneWallet.usecases.user

import com.onewallet.OneWallet.core.entities.Otp
import com.onewallet.OneWallet.core.entities.User
import com.onewallet.OneWallet.core.entities.VerifyUser
import com.onewallet.OneWallet.usecases.UseCase
import com.onewallet.OneWallet.usecases.exceptions.BusinessException
import com.onewallet.OneWallet.usecases.exceptions.IncorrectVerificationCodeException
import com.onewallet.OneWallet.usecases.gateways.util.OtpUtil
import com.onewallet.OneWallet.usecases.gateways.util.PasswordHasher

class VerifyUserUseCase(private val userRepository: UserRepository,
                        private val otpRepository: OtpRepository,
                        private val otpUtil: OtpUtil,
                        private val passwordHasher: PasswordHasher
) : UseCase<VerifyUser, Unit> {

    override fun execute(request: VerifyUser) {

        val user = userRepository.findByPhoneNumber(request.phoneNumber) ?: throw BusinessException("user not found")
        val otp = otpRepository.findTopByPhoneNumberOrderByCreatedAtDesc(request.phoneNumber) ?: throw BusinessException("no otp have been sent")
        val verified = otpUtil.verify(request.verificationCode, otp.secret, otp.timeInterval, otp.alg, otp.digits)
        if (!verified) {
            throw IncorrectVerificationCodeException("otp not matched")
        }
        user.password = passwordHasher.hash(request.password)
        user.registrationId = request.registrationId
        user.verifiedByPhoneNumber = true
        userRepository.save(user)
    }

    interface UserRepository {
        fun save(user: User)
        fun findByPhoneNumber(phoneNumber: String): User?
    }

    interface OtpRepository {
        fun findTopByPhoneNumberOrderByCreatedAtDesc(phoneNumber: String): Otp?
    }

}