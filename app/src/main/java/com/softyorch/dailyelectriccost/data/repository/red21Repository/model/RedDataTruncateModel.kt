package com.softyorch.dailyelectriccost.data.repository.red21Repository.model

data class RedDataTruncateModel(
    val category: String,
    var widget: String,
    var startDate: String,
    var endDate: String,
    var timeTruncate: String,
    var geo_limit: String,
    var geo_ids: String
)
