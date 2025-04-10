package com.kaleci.ui.order

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _orderUiState = MutableStateFlow(OrderUiState())
    val orderUiState = _orderUiState.asStateFlow()



    fun onEvent(event: OrderEVent) {
        when (event) {
            is OrderEVent.OnOrderPlaced -> {
                // Handle order placed event
            }
            is OrderEVent.OnOrderCancelled -> {
                // Handle order cancelled event
            }
            is OrderEVent.OnOrderUpdated -> {
                // Handle order updated event
            }


        }
    }

}

data class OrderUiState(
    val orderId: String = "",
    val orderDetails: String = "",
    val items: List<String> = emptyList(),
    val packagesList: List<String> = emptyList(),
    val isLoading: Boolean = false
)

sealed class OrderEVent {
    object OnOrderPlaced : OrderEVent()
    object OnOrderCancelled : OrderEVent()
    data class OnOrderUpdated(val orderId: String) : OrderEVent()
}
