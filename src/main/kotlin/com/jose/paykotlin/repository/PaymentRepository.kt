package com.jose.paykotlin.repository

import com.jose.paykotlin.domain.PaymentDetails
import org.springframework.data.mongodb.repository.MongoRepository

interface PaymentRepository : MongoRepository<PaymentDetails, String> {
    fun findByUserId(userId: String): List<PaymentDetails>
}
