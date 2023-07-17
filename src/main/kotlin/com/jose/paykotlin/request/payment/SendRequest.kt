package com.jose.paykotlin.request.payment

class SendRequest(
    val userId: String,
    val receiveUserId: String,
    val money: Long
) : PaymentTypeRequest()
