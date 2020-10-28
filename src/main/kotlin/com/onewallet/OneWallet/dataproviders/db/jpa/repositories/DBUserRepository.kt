package com.onewallet.OneWallet.dataproviders.db.jpa.repositories

import com.onewallet.OneWallet.dataproviders.db.jpa.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DBUserRepository: JpaRepository<UserEntity, Double>