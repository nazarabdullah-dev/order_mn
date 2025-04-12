package com.kaleci.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaleci.domain.model.Item
import com.kaleci.domain.model.Package
import com.kaleci.domain.usecase.GetItemListUseCase
import com.kaleci.domain.usecase.PackageItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    val getItemListUseCase: GetItemListUseCase,
    val packageItemsUseCase: PackageItemsUseCase,
) : ViewModel() {

    private val _itemsListState = MutableStateFlow(ItemsListState())
    val itemsListState = _itemsListState.asStateFlow()


    private val _packageListState = MutableStateFlow(PackageListState())
    val packageListState = _packageListState.asStateFlow()

    // make a delay of 1 second to simulate loading
    init {
        viewModelScope.launch {
            _itemsListState.update {
                it.copy(
                    isLoading = true
                )
            }
            delay(1000)
            _itemsListState.update {
                it.copy(
                    items = getItemListUseCase.invoke(),
                    isLoading = false
                )
            }
        }
    }


    fun onEvent(event: OrderEVent) {
        when (event) {
            is OrderEVent.OnOrderPlaced -> {
                _packageListState.update {
                    it.copy(
                        isLoading = true
                    )
                }
                val itemsListState =
                    packageItemsUseCase(itemsListState.value.items.filter { item -> item.selected })
                _packageListState.update {
                    it.copy(
                        packages = itemsListState,
                        isLoading = false
                    )
                }
            }

            is OrderEVent.OnOrderCancelled -> {
                _itemsListState.update {
                    it.copy(
                        items = it.items.map { item ->
                            item.copy(selected = false)
                        },
                    )
                }
                _packageListState.update {
                    it.copy(
                        packages = emptyList()
                    )
                }
            }

            is OrderEVent.OnOrderUpdated -> {
                _itemsListState.update {
                    it.copy(
                        items = it.items.map { item ->
                            if (item.name == event.item.name) {
                                item.copy(selected = !item.selected)
                            } else {
                                item
                            }
                        }
                    )
                }
            }

            is OrderEVent.OnSelectAll ->
                _itemsListState.update {
                    it.copy(
                        items = it.items.map { item ->
                            item.copy(selected = event.selectAll)
                        }
                    )
                }
        }
    }

}

data class ItemsListState(
    val items: List<Item> = emptyList(),
    val isLoading: Boolean = true
) {
    fun isItemSelected() = items.any { it.selected }
    fun getSelectedItemsCount(): Int {
        return items.count { it.selected }
    }

    fun isAllItemSelected(): Boolean {
        return items.all { it.selected }
    }
}

data class PackageListState(
    val packages: List<Package> = emptyList(),
    val isLoading: Boolean = false,
) {
    fun getTotalPrice(): Double {
        return packages.sumOf { it.totalPrice.toDouble() }
    }

    fun getTotalWeight(): Int {
        return packages.sumOf { it.totalWeight }
    }

    fun getCourierPrice(): Double {
        return packages.size * 15.0
    }

    fun isHeaviestPackage(item: Package): Boolean {
        return packages.maxOfOrNull { it.totalWeight } == item.totalWeight
    }

}

sealed class OrderEVent {
    object OnOrderPlaced : OrderEVent()
    object OnOrderCancelled : OrderEVent()
    data class OnOrderUpdated(val item: Item) : OrderEVent()
    data class OnSelectAll(val selectAll: Boolean) : OrderEVent()
}
