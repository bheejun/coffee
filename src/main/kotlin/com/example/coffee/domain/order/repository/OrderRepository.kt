package com.example.coffee.domain.order.repository

import com.example.coffee.domain.order.model.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface OrderRepository : JpaRepository<Order, Long>{

    @Query("SELECT o.menu, COUNT(o) as orderCount FROM Order o WHERE o.orderedAt >= :startDate GROUP BY o.menu ORDER BY orderCount DESC")
    fun findPopularMenusSince(startDate: LocalDateTime): List<Array<Any>>

}