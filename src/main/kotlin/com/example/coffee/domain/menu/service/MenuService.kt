package com.example.coffee.domain.menu.service

import com.example.coffee.domain.menu.dto.PopularMenuResponse
import com.example.coffee.domain.menu.model.Menu

interface MenuService {
    fun getMenuList() : List<Menu>

    fun findPopularMenus() : List<PopularMenuResponse>
}