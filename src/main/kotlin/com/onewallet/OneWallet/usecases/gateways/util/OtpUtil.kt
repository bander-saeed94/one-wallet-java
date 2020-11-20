package com.onewallet.OneWallet.usecases.gateways.util

import com.onewallet.OneWallet.core.entities.Otp

interface OtpUtil{
    fun generate(timeInterval: Int, alg: String, digits: Int): Otp
    fun verify(
            enteredToken: String,
            secret: String,
            timeInterval: Int,
            alg: String,
            digits: Int
    ): Boolean;
}