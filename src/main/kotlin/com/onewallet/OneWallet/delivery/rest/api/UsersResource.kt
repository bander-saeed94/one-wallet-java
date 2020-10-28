package com.onewallet.OneWallet.delivery.rest.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletionStage

@RestController
@RequestMapping("users")
interface UsersResource {

    @GetMapping("/{phoneNumber}")
    fun registerUser(@PathVariable("phoneNumber") phoneNumber: Double): CompletionStage<ResponseEntity<Unit>>;

}