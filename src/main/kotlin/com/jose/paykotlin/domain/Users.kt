package com.jose.paykotlin.domain

import com.jose.paykotlin.domain.enum.BANK
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "users")
class Users(
    @Id
    val userId: String,
    val email: String,
    var bank: HashSet<BANK>, // 은행 이름(농협, 국민 등)
    var money: Long // 은행에서 충전한 앱 내 포인트
)
