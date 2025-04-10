package com.kaleci.domain.usecase

import com.kaleci.domain.model.Item
import com.kaleci.domain.repository.ItemRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetItemListUseCase @Inject constructor(
    private val itemRepository: ItemRepository
) {

    operator fun invoke(): List<Item> {
        return itemRepository.getItems()
    }
}
