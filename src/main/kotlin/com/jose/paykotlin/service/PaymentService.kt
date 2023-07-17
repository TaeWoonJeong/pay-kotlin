package com.jose.paykotlin.service

import com.jose.paykotlin.domain.PaymentDetails
import com.jose.paykotlin.domain.enum.BANK
import com.jose.paykotlin.domain.enum.PAYMENT_TYPE
import com.jose.paykotlin.logger
import com.jose.paykotlin.repository.PaymentRepository
import com.jose.paykotlin.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.UUID

@Service
class PaymentService(
    private val userRepository: UserRepository,
    private val paymentRepository: PaymentRepository
) {
    val log = logger()

    fun payment(userId: String): List<PaymentDetails> {
        userRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException()
        return paymentRepository.findByUserId(userId)
    }

    @Transactional
    fun charge(userId: String, money: Long, bank: BANK, paymentType: PAYMENT_TYPE) {
        if (paymentType.compareTo(PAYMENT_TYPE.CHARGE) == 0) {
            val user = userRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException()
            if (user.bank.contains(bank)) {
                paymentRepository.save(
                    PaymentDetails(
                        paymentId = UUID.randomUUID().toString(),
                        paymentType = PAYMENT_TYPE.CHARGE,
                        userId = userId,
                        money = money,
                        sendDate = LocalDateTime.now(),
                        bank = bank
                    )
                )
                user.money += money
                userRepository.save(user)
            } else {
                throw IllegalArgumentException("은행 등록 안함")
            }
        } else {
            log.info("충전 실패 : type 에러")
        }
    }

    fun send(sendUserId: String, receiveUserId: String, money: Long, paymentType: PAYMENT_TYPE) {
        if (paymentType.compareTo(PAYMENT_TYPE.SEND) == 0) {
            val sendUser = userRepository.findByIdOrNull(sendUserId) ?: throw IllegalArgumentException()
            val receiveUser = userRepository.findByIdOrNull(receiveUserId) ?: throw IllegalArgumentException()
            if (sendUser.money < money) {
                throw IllegalArgumentException("송금 실패 : 가진돈이 송금할 돈보다 적습니다.")
            }
            paymentRepository.save(
                PaymentDetails(
                    paymentId = UUID.randomUUID().toString(),
                    paymentType = PAYMENT_TYPE.SEND,
                    userId = sendUserId,
                    receiveUserId = receiveUserId,
                    money = money, // 보내는 돈 기록
                    sendDate = LocalDateTime.now()
                )
            )
            sendUser.money -= money
        } else {
            log.info("송금 실패 : type 에러")
        }
    }

    fun cancel(userId: String, paymentId: String, paymentType: PAYMENT_TYPE) {
        if (paymentType.compareTo(PAYMENT_TYPE.CANCEL) == 0) {
            val sendUser = userRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException()
            val paymentDetail = paymentRepository.findByIdOrNull(paymentId) ?: throw IllegalArgumentException()
            if (paymentDetail.userId == sendUser.userId) {
                paymentDetail.paymentType = PAYMENT_TYPE.CANCEL
                sendUser.money += paymentDetail.money
            } else {
                log.info("송금 취소 실패 : 송금한 유저와 내역이 맞지 않습니다.")
            }
        } else {
            log.info("송금 취소 실패 : type 에러")
        }
    }

    fun receive(userId: String, paymentId: String, paymentType: PAYMENT_TYPE) {
        if (paymentType.compareTo(PAYMENT_TYPE.RECEIVE) == 0) {
            val receiveUser = userRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException()
            val paymentDetail = paymentRepository.findByIdOrNull(paymentId) ?: throw IllegalArgumentException()
            if (paymentDetail.receiveUserId == receiveUser.userId) {
                receiveUser.money += paymentDetail.money
                paymentDetail.paymentType = PAYMENT_TYPE.RECEIVE
                paymentDetail.receiveDate = LocalDateTime.now()
            } else {
                log.info("송금 받기 실패 : 받는 유저가 다릅니다.")
            }
        } else {
            log.info("송금 받기 실패 : tpye 에러")
        }
    }
}
