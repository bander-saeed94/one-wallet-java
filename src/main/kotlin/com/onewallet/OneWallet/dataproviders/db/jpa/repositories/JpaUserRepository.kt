package com.onewallet.OneWallet.dataproviders.db.jpa.repositories

import com.onewallet.OneWallet.core.entities.User
import com.onewallet.OneWallet.dataproviders.db.jpa.entities.toUserEntity
import com.onewallet.OneWallet.usecases.gateways.UserRepository

open class JpaUserRepository(private val dbUserRepository: DBUserRepository) : UserRepository {
    override fun save(user: User) {
        dbUserRepository.save(user.toUserEntity())
    }
}