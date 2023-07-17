package com.jose.paykotlin.controller

import com.jose.paykotlin.domain.Users
import com.jose.paykotlin.request.user.BankRequest
import com.jose.paykotlin.request.user.UserRequest
import com.jose.paykotlin.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private var userService: UserService) {
    @GetMapping("/users")
    fun getUsers(): List<Users> {
        return userService.getUsers()
    }

    @GetMapping("/user/{userId}")
    fun getUser(@PathVariable userId: String): Users {
        return userService.getUser(userId)
    }

    @PostMapping("/user")
    fun addUser(@RequestBody userRequest: UserRequest) {
        userService.addUser(userRequest.userId, userRequest.email)
    }

    //결제 은행 추가
    @PostMapping("/user/bank")
    fun addBank(@RequestBody bankRequest: BankRequest) {
        userService.addBank(bankRequest.userId, bankRequest.email, bankRequest.bank)
    }
}
