package com.jose.paykotlin.domain

import com.jose.paykotlin.domain.enum.BANK
import com.jose.paykotlin.domain.enum.PAYMENT_TYPE
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "payment_details")
class PaymentDetails(
    @Id
    val paymentId: String, // 자동으로 생성할 UUID
    var paymentType: PAYMENT_TYPE,
    val userId: String,
    var receiveUserId: String? = null,
    val money: Long,
    @CreatedDate
    val sendDate: LocalDateTime, // 송금한 날짜
    var receiveDate: LocalDateTime? = null, // 받은 날짜 TYPE이 SEND인 경우만 사용
    val bank: BANK? = null // CHARGE인 경우만 사용
)
