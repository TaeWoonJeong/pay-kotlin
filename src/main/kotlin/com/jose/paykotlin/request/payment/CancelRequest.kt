package com.jose.paykotlin.request.payment

class CancelRequest(
    val userId: String,
    val paymentId: String
) : PaymentTypeRequest()
