package com.softyorch.dailyelectriccost.data.repository.red21Repository.model

data class RedBalanceModel(
    val category: String,
    val widget: String,
    val startDate: String,
    val endDate: String,
    val timeTruncate: String
)
