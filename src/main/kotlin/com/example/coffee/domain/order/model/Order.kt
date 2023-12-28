package com.example.coffee.domain.order.model

import com.example.coffee.domain.menu.model.Menu
import com.example.coffee.domain.user.model.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long ? = null,

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: User,

    @ManyToOne
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    val menu: Menu,

    val orderedAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    val amount: Int
)
