package com.onewallet.OneWallet.core.entities

data class Otp(
        val token: String,
        val secret: String,
        val alg: String,
        val timeInterval: Int,
        val digits: Int
)