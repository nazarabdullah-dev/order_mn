package com.kaleci.domain

import com.kaleci.domain.model.Item
import com.kaleci.domain.model.Package
import com.kaleci.domain.usecase.PackageItemsUseCase
import org.junit.Assert.assertEquals
import org.junit.Test


class PackageItemsUseCaseTest {

    private val packageItemsUseCase = PackageItemsUseCase()

    @Test
    fun `invoke should return empty list when selectedItems is empty`() {
        // Arrange
        val selectedItems = emptyList<Item>()

        // Act
        val result = packageItemsUseCase(selectedItems)

        // Assert
        assertEquals(emptyList<Package>(), result)
    }

    @Test
    fun `invoke should return one package when total price is less than or equal to 250`() {
        // Arrange
        val selectedItems = listOf(
            Item(name = "Item1", price = 100, weight = 10),
            Item(name = "Item2", price = 50, weight = 5)
        )

        // Act
        val result = packageItemsUseCase(selectedItems)

        // Assert
        assertEquals(1, result.size)
        assertEquals(150, result[0].totalPrice)
        assertEquals(15, result[0].totalWeight)
    }

    @Test
    fun `invoke should split items into multiple packages when total price exceeds 250`() {
        // Arrange
        val selectedItems = listOf(
            Item(name = "Item1", price = 200, weight = 20),
            Item(name = "Item2", price = 100, weight = 10),
            Item(name = "Item3", price = 50, weight = 5)
        )

        // Act
        val result = packageItemsUseCase(selectedItems)

        // Assert
        assertEquals(2, result.size)
        assertEquals(200, result[0].totalPrice)
        assertEquals(150, result[1].totalPrice)
    }
}
