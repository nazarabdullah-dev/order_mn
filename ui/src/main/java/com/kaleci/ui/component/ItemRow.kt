package com.kaleci.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaleci.domain.model.Item
import com.kaleci.ui.theme.Order_appTheme

@Composable
fun ItemRow(item: Item, modifier: Modifier = Modifier, onCheckedChange: () -> Unit) {

    Card(
        onClick = onCheckedChange,
        border = if (item.selected)
            BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary
            ) else {
            null

        },
        modifier = modifier


            .fillMaxWidth()
            .height(130.dp)
    ) {
        Text(
            text = "${item.name} ",
            softWrap = true,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${item.weight}g",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(8.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "${item.price} USD",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))

    }
    // Implement the UI for each item row

}

@Preview
@Composable
fun ItemRowUnSelectedPreview() {
    Order_appTheme {
        ItemRow(
            item =
                Item(
                    name = "Item 1",
                    price = 100,
                    weight = 10,
                    selected = false
                ), onCheckedChange = {})
    }
}


@Preview
@Composable
fun ItemRowSelectedPreview() {
    Order_appTheme {
        ItemRow(
            modifier = Modifier.width(130.dp),
            item =
                Item(
                    name = "Item 1",
                    price = 100,
                    weight = 10,
                    selected = true
                ), onCheckedChange = {})
    }
}
