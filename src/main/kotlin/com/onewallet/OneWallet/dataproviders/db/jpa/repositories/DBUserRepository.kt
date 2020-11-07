package com.onewallet.OneWallet.dataproviders.db.jpa.repositories

import com.onewallet.OneWallet.dataproviders.db.jpa.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DBUserRepository: JpaRepository<UserEntity, UUID>{
    fun findByPhoneNumber(phoneNumber: String): UserEntity?
}