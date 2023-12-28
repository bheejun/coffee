package com.example.coffee.domain.user.model

import com.example.coffee.domain.user.dto.UserResponse
import jakarta.persistence.*


@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? =null,

    @Column(nullable = false)
    var username : String,

    @Column(nullable = false)
    var password : String,

    @Column(nullable = false)
    var points: Int = 0,

    @Version
    var version: Long?= null
)

fun User.toResponse(): UserResponse {
    return UserResponse(
        username = username,
        password = password
    )
}