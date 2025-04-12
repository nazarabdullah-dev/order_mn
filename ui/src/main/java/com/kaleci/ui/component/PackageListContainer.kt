package com.kaleci.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect.Companion.dashPathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaleci.domain.model.Package
import com.kaleci.ui.order.PackageListState

@Composable
fun PackageListContainer(packageListState: PackageListState, modifier: Modifier = Modifier) {


    if (packageListState.packages.isNotEmpty()) {
        Spacer(
               modifier = Modifier.height(8.dp)
        )
        Text("Packages: ${packageListState.packages.size}", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(horizontal = 10.dp))
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        LazyColumn(modifier = modifier) {

            itemsIndexed(packageListState.packages) { index, pkg ->
                val isHeaviestPackage by remember {
                    derivedStateOf {
                        packageListState.isHeaviestPackage(pkg)
                    }
                }

                PackageContainer(index, pkg,isHeaviestPackage)
            }
        }

    }
}

@Composable
private fun PackageContainer(index: Int, pkg: Package,isHeaviestPackage: Boolean) {

    Box(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)) // Add rounded corners

            .drawBehind {
                val strokeWidth = 4.dp.toPx()
                val dashWidth = 10.dp.toPx()
                val dashGap = 5.dp.toPx()
                drawRoundRect(
                    color = Color.Black,
                    size = size,
                    cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx()),
                    style = Stroke(
                        width = strokeWidth,
                        pathEffect = dashPathEffect(
                            intervals = floatArrayOf(dashWidth, dashGap),
                            phase = 0f
                        )
                    )
                )
            }
            .padding(8.dp)

    ) {
        Column  {
            Text("Package :${index + 1}", style = MaterialTheme.typography.titleSmall)
            Text("- Items: ${pkg.items.size}")
            Text("- Total Price: $${pkg.totalPrice}")
            Text("- Total Weight: ${pkg.totalWeight}g")
            Text("- Courier: $${pkg.courierPrice}")
            Spacer(modifier = Modifier.height(8.dp))
        }
        Column(modifier = Modifier.align(Alignment.TopEnd)) {
            if (isHeaviestPackage) {
                Text(
                    text = "Heaviest",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.Red)
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

        }

    }
}

@Preview
@Composable
fun PackageListContainerPreview() {
    PackageListContainer(
        packageListState = PackageListState(
            packages = listOf(
                Package(
                    items = listOf(),
                    totalPrice = 100,
                    totalWeight = 200,
                    courierPrice = 10.0
                ),
                Package(
                    items = listOf(),
                    totalPrice = 200,
                    totalWeight = 300,
                    courierPrice = 20.0
                )
            )
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
