package com.kaleci.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaleci.ui.theme.Order_appTheme

@Composable
fun ItemRow(item: Int,modifier: Modifier = Modifier.fillMaxWidth(), onCheckedChange: () -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Item $item", modifier = Modifier.padding(8.dp))
        Button(onClick = onCheckedChange) {
            Text("Select")
        }
    }
    // Implement the UI for each item row

}

@Preview
@Composable
fun ItemRowPreview() {
    Order_appTheme {
        ItemRow(item = 1, onCheckedChange = {})
    }
}
