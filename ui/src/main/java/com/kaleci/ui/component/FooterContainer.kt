package com.kaleci.ui.component

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaleci.ui.order.ItemsListState
import com.kaleci.ui.order.OrderEVent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
 fun FooterContainer(
    itemsListState: ItemsListState,
    coroutineScope: CoroutineScope,
    bottomSheetState: SheetState,
    modifier : Modifier = Modifier,
    event: (OrderEVent) -> Unit
) {

    AnimatedVisibility(
        visible = itemsListState.isItemSelected(),
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        modifier = modifier
            .drawBehind {
                Log.d("recomposition", "being drawn footer")
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .drawBehind {
                    drawRect(
                        brush = Brush.linearGradient(
                            start = Offset(x = 0f, y = 0f),
                            end = Offset(x = 0f, y = 40f),
                            colors = listOf(
                                Color.Transparent, // Start color
                                Color.White  // End color
                            )
                        )
                    )
                }

        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        bottomSheetState.show()
                    }
                    event(OrderEVent.OnOrderPlaced)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .drawBehind {
                        Log.d("recomposition", "being drawn button")
                    }
            ) {
                Text("Place Order")
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Text(
                        text = " ${itemsListState.getSelectedItemsCount()}",
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun FooterContainerPreview() {
    FooterContainer(
        itemsListState = ItemsListState(),
        coroutineScope = rememberCoroutineScope(),
        bottomSheetState = rememberModalBottomSheetState(),
        event = {}
    )
}
