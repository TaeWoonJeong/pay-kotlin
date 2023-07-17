package com.jose.paykotlin.repository

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class RedisLockRepositoryImpl(
    private val stringRedisTemplate: StringRedisTemplate
) : RedisLockRepository{
    override fun lock(key: Long): Boolean {
        return stringRedisTemplate.opsForValue().setIfAbsent(key.toString(),"lock", Duration.ofMillis(3000))!!
    }

    override fun unlock(key: Long): Boolean {
        return stringRedisTemplate.delete(key.toString())
    }
}