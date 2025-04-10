package com.kaleci.domain.model

data class Package(
    val items: List<Item>,
    val totalPrice: Int,
    val totalWeight: Int,
    val courierPrice: Double = 15.0
)
