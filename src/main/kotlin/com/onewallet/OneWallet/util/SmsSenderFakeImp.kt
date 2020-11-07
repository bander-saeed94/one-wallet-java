package com.onewallet.OneWallet.util

import com.onewallet.OneWallet.usecases.gateways.util.SmsSender

class SmsSenderFakeImp : SmsSender {
    override fun sendOtp(phoneNumber: String, otp: String) {
        println("send otp: ${otp} to: ${phoneNumber}")
    }
}