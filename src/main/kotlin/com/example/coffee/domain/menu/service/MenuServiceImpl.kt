package com.example.coffee.domain.menu.service

import com.example.coffee.domain.menu.dto.PopularMenuResponse
import com.example.coffee.domain.menu.model.Menu
import com.example.coffee.domain.menu.repository.MenuRepository
import com.example.coffee.domain.order.repository.OrderRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class MenuServiceImpl(
    private val menuRepository: MenuRepository,
    private val orderRepository: OrderRepository
) : MenuService {
    override fun getMenuList(): List<Menu> = menuRepository.findAll()

    override fun findPopularMenus(): List<PopularMenuResponse> {
        val startDate = LocalDateTime.now().minusDays(7)
        return orderRepository.findPopularMenusSince(startDate).map { result ->
            val menu = result[0] as Menu
            val orderCount = result[1] as Long
            PopularMenuResponse(
                menuId = menu.id,
                name = menu.name,
                description = menu.description,
                price = menu.price,
                orderCount = orderCount
            )
        }
    }

}