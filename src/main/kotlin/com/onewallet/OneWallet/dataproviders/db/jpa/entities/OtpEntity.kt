package com.onewallet.OneWallet.dataproviders.db.jpa.entities

import com.onewallet.OneWallet.core.entities.Otp
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "otps")
class OtpEntity(
        @Id
        val otpId: UUID = UUID.randomUUID(),
        val phoneNumber: String,
        val token: String,
        val secret: String,
        val alg: String,
        val timeInterval: Int,
        val digits: Int,
        val createdAt: LocalDateTime = LocalDateTime.now()
)

fun Otp.toOtpEntity(phoneNumber: String) =
        OtpEntity(
                UUID.randomUUID(),
                phoneNumber,
                token,
                secret,
                alg,
                timeInterval,
                digits
        )
fun OtpEntity.toOtp() = Otp(
        token,
        secret,
        alg,
        timeInterval,
        digits
)