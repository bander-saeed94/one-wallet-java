package com.onewallet.OneWallet.util

import com.onewallet.OneWallet.core.entities.Otp
import com.onewallet.OneWallet.usecases.gateways.util.OtpUtil

class OtpUtilFakeImp : OtpUtil{
    override fun generate(timeInterval: Int, alg: String, digits: Int): Otp {
        val token = when(digits){
            4 -> (0..10000).random().toString().padStart(digits, '0')        // 0-9999
            5 -> (0..100000).random().toString().padStart(digits, '0')       // 0-99999
            6 -> (0..1000000).random().toString().padStart(digits, '0')      // 0-999999
            else -> (0..10000).random().toString().padStart(digits, '0')     // 0-9999
        }
                (0..1000).random().toString(); // 0-9
        println("generated token: ${token}")
        val secret = "secret$token";
        return Otp(
                token,
                secret,
                alg,
                timeInterval,
                digits
        )
    }

    override fun verify(timeInterval: Int, alg: String, digits: Int, enteredToken: Int, secret: String): Boolean {
        if (secret == "secret$enteredToken") {
            return true;
        }
        return false;
    }

}