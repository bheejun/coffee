package com.example.coffee.domain.order.dto

data class OrderResponse(
    val orderId: Long,
    val username: String,
    val menuId: Long,
    val menuName: String,
    val quantity: Int,
    val totalCost: Double,
    val orderTimestamp: String
)
