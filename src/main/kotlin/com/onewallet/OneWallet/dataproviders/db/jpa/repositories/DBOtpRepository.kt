package com.onewallet.OneWallet.dataproviders.db.jpa.repositories

import com.onewallet.OneWallet.dataproviders.db.jpa.entities.OtpEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface DBOtpRepository : JpaRepository<OtpEntity, UUID> {
    fun findTopByPhoneNumberOrderByCreatedAtDesc(phoneNumber: String): OtpEntity?
}