package com.onewallet.OneWallet.dataproviders.db.jpa.entities

import com.onewallet.OneWallet.core.entities.User
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class UserEntity(
        @Id
        val phoneNumber: Double,
        val password: String? = null,
        val registrationId: String? = null,
        val apnRegistrationId: String? = null,
        val gcmRegistrationId: String? = null,
        val verifiedByPhoneNumber: Boolean = false
)

//mappers
fun UserEntity.toUser() =
        User(
                this.phoneNumber,
                this.password,
                this.registrationId,
                this.apnRegistrationId,
                this.gcmRegistrationId,
                this.verifiedByPhoneNumber
        )

fun User.toUserEntity() =
        UserEntity(
                this.phoneNumber,
                this.password,
                this.registrationId,
                this.apnRegistrationId,
                this.gcmRegistrationId,
                this.verifiedByPhoneNumber
        )