package com.onewallet.OneWallet.dataproviders.db.jpa.repositories

import com.onewallet.OneWallet.core.entities.User
import com.onewallet.OneWallet.dataproviders.db.jpa.entities.toUser
import com.onewallet.OneWallet.dataproviders.db.jpa.entities.toUserEntity
import com.onewallet.OneWallet.usecases.gateways.repo.UserRepository

open class JpaUserRepository(private val dbUserRepository: DBUserRepository) : UserRepository {
    override fun save(user: User) {
        dbUserRepository.save(user.toUserEntity())
    }

    override fun findByPhoneNumber(phoneNumber: String): User? {
        return dbUserRepository.findByPhoneNumber(phoneNumber)?.toUser()
    }
}