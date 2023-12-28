package com.example.coffee.domain.order.service

import com.example.coffee.domain.order.dto.OrderResponse

interface OrderService {

    fun placeOrder(username: String, menuId: Long): OrderResponse
}