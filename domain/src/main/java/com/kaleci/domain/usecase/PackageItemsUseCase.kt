package com.kaleci.domain.usecase

import com.kaleci.domain.model.Item
import com.kaleci.domain.model.Package
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class PackageItemsUseCase @Inject constructor() {

    operator fun invoke(selectedItems: List<Item>): List<Package> {
        if (selectedItems.isEmpty()) return emptyList()

        val totalPrice = selectedItems.sumOf { it.price }

        return if (totalPrice <= 250) {
            listOf(
                Package(
                    items = selectedItems,
                    totalPrice = totalPrice,
                    totalWeight = selectedItems.sumOf { it.weight }
                )
            )
        } else {
            splitIntoPackages(selectedItems)
        }
    }

    private fun splitIntoPackages(items: List<Item>): List<Package> {
        val sortedItems = items.sortedByDescending { it.price }
        val packages = mutableListOf<MutableList<Item>>()

        for (item in sortedItems) {
            var added = false

            for (pkg in packages) {
                val pkgTotal = pkg.sumOf { it.price }
                if (pkgTotal + item.price < 250) {
                    pkg.add(item)
                    added = true
                    break
                }
            }

            if (!added) {
                packages.add(mutableListOf(item))
            }
        }

        return packages.map { pkgItems ->
            Package(
                items = pkgItems,
                totalPrice = pkgItems.sumOf { it.price },
                totalWeight = pkgItems.sumOf { it.weight }
            )
        }
    }
}
