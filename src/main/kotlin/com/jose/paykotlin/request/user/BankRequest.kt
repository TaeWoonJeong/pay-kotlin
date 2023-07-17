package com.jose.paykotlin.request.user

import com.jose.paykotlin.domain.enum.BANK

class BankRequest(
    val userId: String,
    val email: String,
    val bank: BANK
)
