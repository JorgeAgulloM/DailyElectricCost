package com.softyorch.dailyelectriccost.data.network.red21Api

data class RedDataTruncateEntity(
    var widget: String,
    var startDate: String,
    var endDate: String,
    var timeTruncate: String,
    var geo_limit: String,
    var geo_ids: String
)
