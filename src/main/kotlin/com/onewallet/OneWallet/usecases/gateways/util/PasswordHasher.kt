package com.onewallet.OneWallet.usecases.gateways.util

interface PasswordHasher {
    fun hash(password: String): String
    fun verify(password: String, hash: String): Boolean
}