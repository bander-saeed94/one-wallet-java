package com.onewallet.OneWallet.delivery.config

import com.onewallet.OneWallet.dataproviders.db.jpa.repositories.DBUserRepository
import com.onewallet.OneWallet.dataproviders.db.jpa.repositories.JpaUserRepository
import com.onewallet.OneWallet.delivery.rest.imp.AccountResourceImp
import com.onewallet.OneWallet.usecases.UseCaseExecutor
import com.onewallet.OneWallet.usecases.UseCaseExecutorImp
import com.onewallet.OneWallet.usecases.gateways.UserRepository
import com.onewallet.OneWallet.usecases.user.RegisterUserByPhoneNumberUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Module {

    //Resources
    @Bean
    fun accountResourceImp(
            useCaseExecutor: UseCaseExecutor,
            registerUserByPhoneNumberUseCase: RegisterUserByPhoneNumberUseCase
    ) = AccountResourceImp(useCaseExecutor, registerUserByPhoneNumberUseCase)

    @Bean
    fun useCaseExecutor() = UseCaseExecutorImp()

    //usecases
    @Bean
    fun registerUserByPhoneNumberUseCase(userRepository: UserRepository) = RegisterUserByPhoneNumberUseCase(userRepository)

    //repositories
    @Bean
    fun userRepository(dbUserRepository: DBUserRepository) = JpaUserRepository(dbUserRepository)
}