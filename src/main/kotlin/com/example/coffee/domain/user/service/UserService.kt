package com.example.coffee.domain.user.service

import com.example.coffee.domain.user.dto.LogInRequest
import com.example.coffee.domain.user.dto.SignUpRequest
import com.example.coffee.domain.user.dto.UserResponse

interface UserService {
    fun signUp(signUpRequest: SignUpRequest) : UserResponse
    fun login(logInRequest: LogInRequest) : String
    fun chargePoints(username: String, amount: Int)
}