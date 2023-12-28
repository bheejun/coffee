package com.example.coffee.domain.order.controller

import com.example.coffee.domain.order.dto.OrderResponse
import com.example.coffee.domain.order.service.OrderServiceImpl
import com.example.coffee.domain.user.service.UserServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController(private val orderServiceImpl: OrderServiceImpl) {

    @PostMapping
    fun placeOrder(@RequestBody userId: String, @RequestBody menuId: Long): ResponseEntity<OrderResponse> {
        val order = orderServiceImpl.placeOrder(userId, menuId)
        return ResponseEntity.ok(order)
    }

}