package com.onewallet.OneWallet.util

import com.onewallet.OneWallet.usecases.gateways.util.PasswordHasher

class PasswordHahserFakeImpl : PasswordHasher{
    override fun hash(password: String): String  = "hash$password"

    override fun verify(password: String, hash: String): Boolean = hash == "hash$password"

}