package com.jose.paykotlin.repository

interface RedisLockRepository {
    fun lock(key:Long): Boolean
    fun unlock(key:Long): Boolean
}