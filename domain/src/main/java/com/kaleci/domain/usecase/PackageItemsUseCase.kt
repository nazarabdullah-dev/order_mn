package com.kaleci.domain.usecase

import com.kaleci.domain.model.Item
import com.kaleci.domain.model.Package
import com.kaleci.domain.repository.ItemRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class PackageItemsUseCase @Inject constructor() {

    operator fun invoke(selectedItems: List<Item>): List<Package> {
        return selectedItems.map { item ->
            val totalPrice = selectedItems.sumOf { it.price }
            val totalWeight = selectedItems.sumOf { it.weight }
            Package(
                items = selectedItems,
                totalPrice = totalPrice,
                totalWeight = totalWeight
            )
        }
    }
}
