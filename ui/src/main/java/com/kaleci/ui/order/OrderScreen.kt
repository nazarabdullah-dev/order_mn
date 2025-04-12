package com.kaleci.ui.order

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.kaleci.domain.model.Item
import com.kaleci.domain.model.Package
import com.kaleci.ui.component.BottomSheetPackageContainer
import com.kaleci.ui.component.FooterContainer
import com.kaleci.ui.component.ItemListContainer
import com.kaleci.ui.theme.Order_appTheme

@Composable
fun OrderScreen(viewModel: OrderViewModel = hiltViewModel()) {
    val itemsListState by viewModel.itemsListState.collectAsState(initial = ItemsListState())
    val packageListState by viewModel.packageListState.collectAsState(initial = PackageListState())
    OrderScreenContent(itemsListState, packageListState, event = viewModel::onEvent)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreenContent(
    itemsListState: ItemsListState,
    packageListState: PackageListState,
    event: (OrderEVent) -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            // Top bar content
            TopAppBar(
                title = {
                    Text("Order App")
                },
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,

                    ) {
                        Text("Select All")
                        Checkbox(
                            checked = itemsListState.isAllItemSelected(),
                            onCheckedChange = {
                                event(OrderEVent.OnSelectAll(it))
                            },
                        )
                    }
                },

                )
        }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            ItemListContainer(itemsListState, event)

            FooterContainer(
                itemsListState = itemsListState,
                coroutineScope, bottomSheetState, modifier = Modifier.align(
                    Alignment.BottomCenter
                ), event
            )
        }
    }
    BottomSheetPackageContainer(bottomSheetState, packageListState)

}


@Preview(showBackground = true)
@Composable
fun OrderScreenContentPreview() {
    Order_appTheme {
        OrderScreenContent(
            ItemsListState(
                items = listOf(
                    Item(
                        name = "Item 1",
                        price = 10,
                        weight = 100
                    ),
                    Item(
                        name = "Item 2",
                        price = 20,
                        weight = 200
                    ),
                    Item(
                        name = "Item 3",
                        price = 30,
                        weight = 300
                    ),
                    Item(
                        name = "Item 4",
                        price = 40,
                        weight = 400
                    ),
                ),
            ),
            PackageListState(
                packages = listOf(
                    Package(
                        items = listOf(
                            Item(
                                name = "Item 1",
                                price = 10,
                                weight = 100
                            ),
                            Item(
                                name = "Item 2",
                                price = 20,
                                weight = 200
                            )
                        ),
                        totalPrice = 30,
                        totalWeight = 300
                    ),
                    Package(
                        items = listOf(
                            Item(
                                name = "Item 3",
                                price = 30,
                                weight = 300
                            ),
                            Item(
                                name = "Item 4",
                                price = 40,
                                weight = 400
                            )
                        ),
                        totalPrice = 70,
                        totalWeight = 700
                    ),
                )
            ),
            event = {}
        )

    }
}
