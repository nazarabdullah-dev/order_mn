package com.kaleci.domain.model

data class Item(
    val name:String,
    val price:Int,
    val weight:Int,
    val selected: Boolean = false
)
