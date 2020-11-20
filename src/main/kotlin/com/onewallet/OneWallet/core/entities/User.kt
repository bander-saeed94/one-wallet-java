package com.onewallet.OneWallet.core.entities

import java.util.*

data class User(
        val userId: UUID,
        val phoneNumber: String,//+966501766627
        var password: String? = null,
        var registrationId: String? = null,
        val apnRegistrationId: String? = null,
        val gcmRegistrationId: String? = null,
        var verifiedByPhoneNumber: Boolean = false
)

fun User.isValid(): Boolean {
    return this.isValidPhoneNumber()
}

fun User.isValidPhoneNumber(): Boolean {
    val phoneNumberRegex = Regex("^\\+9665\\d{8}$")
    return phoneNumberRegex.matches(this.phoneNumber)
}