package com.onewallet.OneWallet.delivery.rest.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletionStage

@RestController
@RequestMapping("/v1/accounts/")
interface AccountsResource {

    @GetMapping("{transport}/code/{phoneNumber}")
    fun createAccount(@PathVariable("transport") transport: String,
                      @PathVariable("phoneNumber") phoneNumber: String
    ): CompletionStage<ResponseEntity<Unit>>

}