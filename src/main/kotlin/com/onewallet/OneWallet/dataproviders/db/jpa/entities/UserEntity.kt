package com.onewallet.OneWallet.dataproviders.db.jpa.entities

import com.onewallet.OneWallet.core.entities.User
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
        @Id
        val userId: UUID = UUID.randomUUID(),
        @Column(unique = true)
        val phoneNumber: String,
        val password: String? = null,
        val registrationId: String? = null,
        val apnRegistrationId: String? = null,
        val gcmRegistrationId: String? = null,
        val verifiedByPhoneNumber: Boolean = false
)

//mappers
fun UserEntity.toUser() =
        User(
                this.userId,
                this.phoneNumber,
                this.password,
                this.registrationId,
                this.apnRegistrationId,
                this.gcmRegistrationId,
                this.verifiedByPhoneNumber
        )

fun User.toUserEntity() =
        UserEntity(
                this.userId,
                this.phoneNumber,
                this.password,
                this.registrationId,
                this.apnRegistrationId,
                this.gcmRegistrationId,
                this.verifiedByPhoneNumber
        )