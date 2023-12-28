package com.example.coffee.domain.menu.controller

import com.example.coffee.domain.menu.model.Menu
import com.example.coffee.domain.menu.service.MenuServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/menus")
class MenuController (
    private val menuServiceImpl: MenuServiceImpl
){

    @GetMapping
    fun listAllMenus(): ResponseEntity<List<Menu>> {
        val menus = menuServiceImpl.getMenuList()
        return ResponseEntity.ok(menus)
    }


}