package com.jose.paykotlin.request.payment

class ReceiveRequest(
    val userId: String,
    val paymentId: String
) : PaymentTypeRequest()
