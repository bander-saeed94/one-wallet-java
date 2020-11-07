package com.onewallet.OneWallet.dataproviders.db.jpa.repositories

import com.onewallet.OneWallet.core.entities.Otp
import com.onewallet.OneWallet.dataproviders.db.jpa.entities.toOtpEntity
import com.onewallet.OneWallet.usecases.gateways.repo.OtpRepository

open class JpaOtpRepository(private val dbOtpRepository: DBOtpRepository) : OtpRepository {
    override fun save(otp: Otp, phoneNumber: String) {
        dbOtpRepository.save(otp.toOtpEntity(phoneNumber))
    }
}