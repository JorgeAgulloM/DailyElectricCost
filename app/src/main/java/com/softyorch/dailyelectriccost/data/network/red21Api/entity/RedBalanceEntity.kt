package com.softyorch.dailyelectriccost.data.network.red21Api.entity

data class RedBalanceEntity(
    val category: String,
    val widget: String,
    val startDate: String,
    val endDate: String,
    val timeTruncate: String
)
