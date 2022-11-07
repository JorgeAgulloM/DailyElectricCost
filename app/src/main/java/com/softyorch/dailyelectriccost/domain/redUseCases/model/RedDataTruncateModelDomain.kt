package com.softyorch.dailyelectriccost.domain.redUseCases.model

data class RedDataTruncateModelDomain(
    val category: String,
    var widget: String,
    var startDate: String,
    var endDate: String,
    var timeTruncate: String,
    var geo_limit: String,
    var geo_ids: String
)
