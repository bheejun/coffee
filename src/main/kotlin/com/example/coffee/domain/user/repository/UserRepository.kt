package com.example.coffee.domain.user.repository

import com.example.coffee.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, String>{
    fun existsByUsername(username: String): Boolean
    fun findByUsername(username: String) : Optional<User>
}