package com.example.coffee.domain.user.controller

import com.example.coffee.domain.user.dto.SignUpRequest
import com.example.coffee.domain.user.model.User
import com.example.coffee.domain.user.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {
    @PostMapping("/signup")
    fun signUp(@Valid @RequestBody signupRequest: SignUpRequest): ResponseEntity<String> {
        userService.signUp(signupRequest)
        return ResponseEntity("User registered successfully", HttpStatus.OK)
    }
    @PostMapping("/charge")
    fun chargePoint(@AuthenticationPrincipal user: UserDetails, @RequestBody amount: Int): ResponseEntity<Void>{
        val username = user.username
        userService.chargePoints(username, amount)
        return ResponseEntity.ok().build()
    }
}