package com.example.coffee.domain.user.service

import com.example.coffee.domain.user.dto.LogInRequest
import com.example.coffee.domain.user.dto.SignUpRequest
import com.example.coffee.domain.user.dto.UserResponse
import com.example.coffee.domain.user.model.User
import com.example.coffee.domain.user.model.toResponse
import com.example.coffee.domain.user.repository.UserRepository
import com.example.coffee.exception.InvalidPasswordException
import com.example.coffee.exception.UserNotFoundException
import com.example.coffee.exception.UsernameAlreadyExistsException
import com.example.coffee.util.JwtUtil
import jakarta.servlet.http.HttpServletResponse
import jakarta.transaction.Transactional
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val response: HttpServletResponse
) : UserService {

    @Transactional
    override fun signUp(signUpRequest: SignUpRequest): UserResponse {
        if (userRepository.existsByUsername(signUpRequest.username)) {
            throw UsernameAlreadyExistsException("Username is already exist")
        }
        val encodedPassword = passwordEncoder.encode(signUpRequest.password)
        return userRepository.save(
            User(
                username = signUpRequest.username,
                password = encodedPassword
            )
        ).toResponse()
    }

    @Transactional
    override fun login(logInRequest: LogInRequest): String {
        val username = logInRequest.username
        val password = logInRequest.password

        val token = jwtUtil.generateToken(username)

        val user = userRepository.findByUsername(username)
            .orElseThrow { UserNotFoundException("Not exist username") }

        if (!passwordEncoder.matches(password, user.password)) {
            throw InvalidPasswordException("Invalid password")
        }

        response.addHeader("Authorization", "Bearer $token")

        return token


    }

    @Transactional
    override fun chargePoints(username: String, amount: Int) {
        val user = userRepository.findByUsername(username).orElseThrow { UserNotFoundException("User not found") }
        user.points += amount
        userRepository.save(user)
    }
}