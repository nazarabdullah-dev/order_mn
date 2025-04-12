package com.kaleci.data.repository

import com.kaleci.domain.model.Item
import com.kaleci.domain.repository.ItemRepository


class ItemRepositoryImpl(): ItemRepository {

    override fun getItems(): List<Item> {
        return listOf(
            Item("Item 1", 10, 200),
            Item("Item 2", 100, 20),
            Item("Item 3", 30, 300),
            Item("Item 4", 20, 500),
            Item("Item 5", 30, 250),
            Item("Item 6", 40, 10),
            Item("Item 7", 200, 10),
            Item("Item 8", 120, 500),
            Item("Item 9", 130, 790),
            Item("Item 10", 20, 100),
            Item("Item 11", 10, 340),
            Item("Item 12", 4, 800),
            Item("Item 13", 5, 200),
            Item("Item 14", 240, 20),
            Item("Item 15", 123, 700),
            Item("Item 16", 245, 10),
            Item("Item 17", 230, 20),
            Item("Item 18", 110, 200),
            Item("Item 19", 45, 200),
            Item("Item 20", 67, 20),
            Item("Item 21", 88, 300),
            Item("Item 22", 10, 500),
            Item("Item 23", 17, 250),
            Item("Item 24", 19, 10),
            Item("Item 25", 89, 10),
            Item("Item 26", 45, 500),
            Item("Item 27", 99, 790),
            Item("Item 28", 125, 100),
            Item("Item 29", 198, 340),
            Item("Item 30", 220, 800),
            Item("Item 31", 249, 200),
            Item("Item 32", 230, 20),
            Item("Item 33", 190, 700),
            Item("Item 34", 45, 10),
            Item("Item 35", 12, 20),
            Item("Item 36", 5, 200),
            Item("Item 37", 2, 200),
            Item("Item 38", 90, 20),
            Item("Item 39", 12, 300),
            Item("Item 40", 167, 500),
            Item("Item 41", 12, 250),
            Item("Item 42", 8, 10),
            Item("Item 43", 2, 10),
            Item("Item 44", 9, 500),
            Item("Item 45", 210, 790),
            Item("Item 46", 167, 100),
            Item("Item 47", 23, 340),
            Item("Item 48", 190, 800),
            Item("Item 49", 199, 200),
            Item("Item 50", 12, 2))
    }
}
