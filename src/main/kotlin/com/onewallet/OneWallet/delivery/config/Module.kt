package com.onewallet.OneWallet.delivery.config

import com.onewallet.OneWallet.dataproviders.db.jpa.repositories.DBOtpRepository
import com.onewallet.OneWallet.dataproviders.db.jpa.repositories.DBUserRepository
import com.onewallet.OneWallet.dataproviders.db.jpa.repositories.JpaOtpRepository
import com.onewallet.OneWallet.dataproviders.db.jpa.repositories.JpaUserRepository
import com.onewallet.OneWallet.delivery.rest.imp.AccountResourceImp
import com.onewallet.OneWallet.usecases.UseCaseExecutor
import com.onewallet.OneWallet.usecases.UseCaseExecutorImp
import com.onewallet.OneWallet.usecases.gateways.repo.OtpRepository
import com.onewallet.OneWallet.usecases.gateways.repo.UserRepository
import com.onewallet.OneWallet.usecases.gateways.util.OtpUtil
import com.onewallet.OneWallet.usecases.gateways.util.PasswordHasher
import com.onewallet.OneWallet.usecases.gateways.util.SmsSender
import com.onewallet.OneWallet.usecases.user.RegisterUserByPhoneNumberUseCase
import com.onewallet.OneWallet.usecases.user.VerifyUserUseCase
import com.onewallet.OneWallet.util.OtpUtilFakeImp
import com.onewallet.OneWallet.util.PasswordHahserFakeImpl
import com.onewallet.OneWallet.util.SmsSenderFakeImp
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Module {

    //Resources
    @Bean
    fun accountResourceImp(
            useCaseExecutor: UseCaseExecutor,
            registerUserByPhoneNumberUseCase: RegisterUserByPhoneNumberUseCase,
            verifyUserUseCase: VerifyUserUseCase
    ) = AccountResourceImp(useCaseExecutor, registerUserByPhoneNumberUseCase, verifyUserUseCase)

    @Bean
    fun useCaseExecutor() = UseCaseExecutorImp()

    //usecases
    @Bean
    fun registerUserByPhoneNumberUseCase(userRepository: UserRepository, otpRepository: OtpRepository, smsSender: SmsSender, otpUtil: OtpUtil)
            = RegisterUserByPhoneNumberUseCase(userRepository, otpRepository, smsSender, otpUtil)

    @Bean
    fun verifyUserUseCase(userRepository: UserRepository, otpRepository: OtpRepository, otpUtil: OtpUtil, passwordHasher: PasswordHasher) = VerifyUserUseCase(userRepository, otpRepository, otpUtil, passwordHasher)

    //repositories
    @Bean
    fun userRepository(dbUserRepository: DBUserRepository) = JpaUserRepository(dbUserRepository)

    @Bean
    fun otpRepository(dbOtpRepository: DBOtpRepository) = JpaOtpRepository(dbOtpRepository)

    //util
    @Bean
    fun smsSender() = SmsSenderFakeImp()

    @Bean
    fun otpUtil() = OtpUtilFakeImp()

    @Bean
    fun passwordHasher() = PasswordHahserFakeImpl()
}