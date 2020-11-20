package com.onewallet.OneWallet.core.entities

data class VerifyUser(
        val verificationCode: String,
        val phoneNumber: String,
        val password: String,
        val registrationId: String
)