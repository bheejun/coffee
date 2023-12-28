package com.example.coffee.domain.menu.repository

import com.example.coffee.domain.menu.model.Menu
import org.springframework.data.jpa.repository.JpaRepository

interface MenuRepository : JpaRepository<Menu, Long>{

}