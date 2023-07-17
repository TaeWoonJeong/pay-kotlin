package com.jose.paykotlin.service

import com.jose.paykotlin.domain.Users
import com.jose.paykotlin.domain.enum.BANK
import com.jose.paykotlin.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun getUsers(): List<Users> {
        return userRepository.findAll()
    }

    fun getUser(userId: String): Users {
        return userRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException()
    }

    fun addUser(userId: String, email: String) {
        if(userRepository.findByIdOrNull(userId)==null) {
            userRepository.save(
                Users(
                    userId = userId,
                    email = email,
                    bank = HashSet(),
                    money = 0
                )
            )
        }
        else {
            throw IllegalArgumentException()
        }
    }

    fun addBank(userId: String, email: String, bank: BANK) {
        val user = userRepository.findByUserIdAndEmail(userId, email) ?: throw IllegalArgumentException()
        user.bank.add(bank)
        userRepository.save(user)
    }
}
