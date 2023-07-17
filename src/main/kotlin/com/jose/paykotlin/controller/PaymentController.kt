package com.jose.paykotlin.controller

import com.jose.paykotlin.domain.PaymentDetails
import com.jose.paykotlin.request.payment.CancelRequest
import com.jose.paykotlin.request.payment.ChargeRequest
import com.jose.paykotlin.request.payment.ReceiveRequest
import com.jose.paykotlin.request.payment.SendRequest
import com.jose.paykotlin.service.PaymentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PaymentController(private val paymentService: PaymentService) {

    //내역 가져오기
    @GetMapping("/payment/{userId}")
    fun getUserPayment(@PathVariable userId: String): List<PaymentDetails> {
        return paymentService.payment(userId)
    }

    @PostMapping("/charge")
    fun charge(@RequestBody chargeRequest: ChargeRequest) {
        paymentService.charge(chargeRequest.userId, chargeRequest.money, chargeRequest.bank, chargeRequest.paymentType)
    }

    @PostMapping("/send")
    fun send(@RequestBody sendRequest: SendRequest) {
        paymentService.send(sendRequest.userId, sendRequest.receiveUserId, sendRequest.money, sendRequest.paymentType)
    }

    @PostMapping("/cancel")
    fun cancel(@RequestBody cancelRequest: CancelRequest) {
        paymentService.cancel(cancelRequest.userId, cancelRequest.paymentId, cancelRequest.paymentType)
    }

    @PostMapping("/receive")
    fun receive(@RequestBody receiveRequest: ReceiveRequest) {
        paymentService.receive(receiveRequest.userId, receiveRequest.paymentId, receiveRequest.paymentType)
    }
}
