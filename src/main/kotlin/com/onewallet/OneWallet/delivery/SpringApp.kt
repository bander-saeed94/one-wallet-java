package com.onewallet.OneWallet.delivery;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = [
    "com.onewallet.OneWallet.delivery.config",
    "com.onewallet.OneWallet.dataproviders.db.jpa.config",
    "com.onewallet.OneWallet.delivery.rest.imp"
])

class SpringApp

fun main(args: Array<String>) {
    runApplication<SpringApp>(*args)
}
