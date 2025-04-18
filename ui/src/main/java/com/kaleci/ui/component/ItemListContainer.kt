package com.kaleci.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kaleci.ui.order.ItemsListState
import com.kaleci.ui.order.OrderEVent
import com.kaleci.ui.util.shimmerBrush

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ItemListContainer(
    state: ItemsListState,
    modifier: Modifier = Modifier,
    event: (OrderEVent) -> Unit
) {

    AnimatedContent(
        targetState = state.isLoading && state.items.isEmpty(),
        label = "ItemListContainer",
        transitionSpec = {
            fadeIn() togetherWith
                    fadeOut()
        }
    ) { isLoading ->

            when {
                isLoading -> {
                    LazyVerticalGrid(
                        modifier = modifier,
                        columns = GridCells.Adaptive(150.dp),
                    ) {
                        items(4) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(130.dp)
                                        .background(brush = shimmerBrush())
                                ) { }
                            }
                        }
                    }
                }

                !isLoading && state.items.isEmpty() -> {
                    Box(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(10.dp)
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No items available.",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.Companion.align(Alignment.Center)
                        )
                    }
                }

                else -> {

                    LazyVerticalGrid(
                        modifier = modifier,
                        columns = GridCells.Adaptive(150.dp),
                    ) {
                        items(state.items.size, key = {
                            state.items[it].name
                        }, span = {
                            GridItemSpan(1)
                        }) { index ->
                            ItemRow(
                                item = state.items[index],
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                onCheckedChange = {
                                    event(OrderEVent.OnOrderUpdated(state.items[index]))
                                }
                            )
                        }

                        item(span = {
                            GridItemSpan(2)
                        }) {
                            Spacer(modifier = Modifier.height(100.dp))
                        }
                    }
                }
            }
        }

}
