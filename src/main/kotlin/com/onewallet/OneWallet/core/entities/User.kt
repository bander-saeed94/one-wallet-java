package com.onewallet.OneWallet.core.entities

data class User(
        val phoneNumber: Double,//966501766627
        val password: String? = null,
        val registrationId: String? = null,
        val apnRegistrationId: String? = null,
        val gcmRegistrationId: String? = null,
        val verifiedByPhoneNumber: Boolean = false
)


fun User.isValid(): Boolean {
    return this.isValidPhoneNumber()
}

fun User.isValidPhoneNumber(): Boolean {
    println(this.phoneNumber.toBigDecimal().toPlainString())
    val phoneNumberRegex = Regex("^9665\\d{8}$")
    return phoneNumberRegex.matches(this.phoneNumber.toBigDecimal().toPlainString())
}