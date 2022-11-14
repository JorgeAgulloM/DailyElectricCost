package com.softyorch.dailyelectriccost.ui.model

data class RedMarketsTruncateModelUi(
    val category: String,
    var widget: String,
    var startDate: String,
    var endDate: String,
    var timeTruncate: String,
    var geo_limit: String,
    var geo_ids: String
)