package com.jose.paykotlin.request.payment

import com.jose.paykotlin.domain.enum.BANK

class ChargeRequest(
    val userId: String,
    val money: Long,
    val bank: BANK
) : PaymentTypeRequest()
