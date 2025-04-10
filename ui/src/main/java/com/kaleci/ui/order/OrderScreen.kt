package com.kaleci.ui.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kaleci.ui.theme.Order_appTheme

@Composable
fun OrderScreen(viewModel: OrderViewModel = hiltViewModel()) {
    val state by viewModel.orderUiState.collectAsState(initial = OrderUiState())
    OrderScreenContent(state = state, event = viewModel::onEvent)
}


@Composable
fun OrderScreenContent(state: OrderUiState, event: (OrderEVent) -> Unit) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            Text("Select Items", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(
                    state.items.size,
                ) { item ->
                    ItemRow(item = item, onCheckedChange = {
                        event(OrderEVent.OnOrderUpdated(state.items[item]))
                    })
                }
            }

            Button(
                onClick = { event(OrderEVent.OnOrderPlaced) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Text("Place Order")
            }

            if (state.packagesList.isNotEmpty()) {
                Text("Packages:", style = MaterialTheme.typography.titleMedium)
                state.packagesList.forEachIndexed { index, pkg ->
                    Text("Package ${index + 1}:")
                    Text("- Items: ${pkg}")
//                Text("- Total Price: $${pkg.totalPrice}")
//                Text("- Total Weight: ${pkg.totalWeight}g")
//                Text("- Courier: $${pkg.courierPrice}")
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }

}

@Composable
fun ItemRow(item: Int, onCheckedChange: () -> Unit) {
    // Implement the UI for each item row
    Text(text = "Item $item", modifier = Modifier.padding(8.dp))
    Button(onClick = onCheckedChange) {
        Text("Select")
    }
}


@Preview(showBackground = true)
@Composable
fun OrderScreenContentPreview() {
    Order_appTheme {
        OrderScreenContent(
            state = OrderUiState(
                items = listOf("Item 1", "Item 2", "Item 3"),
                packagesList = listOf("Package 1", "Package 2")
            ),
            event = {}
        )

    }
}
