package com.softyorch.dailyelectriccost.model

data class RedDataTruncateModelUi(
    var widget: String,
    var startDate: String,
    var endDate: String,
    var timeTruncate: String,
    var geo_limit: String,
    var geo_ids: String
)