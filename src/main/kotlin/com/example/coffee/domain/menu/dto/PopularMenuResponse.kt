package com.example.coffee.domain.menu.dto

data class PopularMenuResponse(

    val menuId: Long,
    val name: String,
    val description: String,
    val price: Double,
    val orderCount: Long

)