package com.kaleci.domain.repository

import com.kaleci.domain.model.Item

interface ItemRepository {

    fun getItems(): List<Item>
}
