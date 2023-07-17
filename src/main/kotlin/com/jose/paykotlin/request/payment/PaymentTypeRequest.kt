package com.jose.paykotlin.request.payment

import com.jose.paykotlin.domain.enum.PAYMENT_TYPE

open class PaymentTypeRequest {
    lateinit var paymentType: PAYMENT_TYPE
}
