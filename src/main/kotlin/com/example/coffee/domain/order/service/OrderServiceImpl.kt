package com.example.coffee.domain.order.service

import com.example.coffee.domain.menu.repository.MenuRepository
import com.example.coffee.domain.order.dto.OrderResponse
import com.example.coffee.domain.order.model.Order
import com.example.coffee.domain.order.repository.OrderRepository
import com.example.coffee.domain.user.repository.UserRepository
import com.example.coffee.exception.UserNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val userRepository: UserRepository,
    private val menuRepository: MenuRepository
) : OrderService {
    @Transactional
    override fun placeOrder(username: String, menuId: Long): OrderResponse {
        val user = userRepository.findByUsername(username).orElseThrow { UserNotFoundException("User not found") }
        val menu = menuRepository.findById(menuId).orElseThrow { RuntimeException("Menu not found") }

        if (user.points < menu.price) {
            throw RuntimeException("Insufficient funds")
        }

        user.points -= menu.price.toInt()
        userRepository.save(user)

        val order = Order(user = user, menu = menu, amount = menu.price.toInt())
        val savedOrder = orderRepository.save(order)


        val orderId = savedOrder.id ?: throw IllegalStateException("Order ID should not be null after saving")

        return OrderResponse(
            orderId = orderId,
            username = user.username,
            menuId = menu.id,
            menuName = menu.name,
            quantity = 1,
            totalCost = menu.price,
            orderTimestamp = savedOrder.orderedAt.toString()
        )
    }


}