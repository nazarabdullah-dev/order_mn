package com.kaleci.data.repository

import com.kaleci.domain.model.Item
import com.kaleci.domain.repository.ItemRepository
import javax.inject.Singleton


@Singleton
class ItemRepositoryImpl(): ItemRepository {

    override fun getItems(): List<Item> {
        return listOf(
            Item("Item 1", 10, 5),
            Item("Item 2", 20, 10),
            Item("Item 3", 30, 15),
            Item("Item 4", 40, 20),
            Item("Item 5", 50, 25)
        )
    }
}
