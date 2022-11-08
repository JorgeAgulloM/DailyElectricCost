package com.softyorch.dailyelectriccost.domain.redUseCases.model

data class RedBalanceModelDomain(
    val category: String,
    val widget: String,
    val startDate: String,
    val endDate: String,
    val timeTruncate: String
)
