package com.softyorch.dailyelectriccost.data.network.red21Api.entity

data class RedDataTruncateEntity(
    val category: String,
    var widget: String,
    var startDate: String,
    var endDate: String,
    var timeTruncate: String,
    var geo_limit: String,
    var geo_ids: String
)
