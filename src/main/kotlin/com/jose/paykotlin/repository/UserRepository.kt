package com.jose.paykotlin.repository

import com.jose.paykotlin.domain.Users
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<Users, String> {
    fun findByUserIdAndEmail(userId: String, email: String): Users?
}
