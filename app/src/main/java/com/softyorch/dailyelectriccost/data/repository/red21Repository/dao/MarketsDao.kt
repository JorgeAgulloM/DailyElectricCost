/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.data.repository.red21Repository.dao

import com.softyorch.dailyelectriccost.utils.Constants.EMPTY_STRING

data class MarketsDao(
    var title: String,
    var lastUpdate: String,
    var type: String,
    var lowPrice: Double,
    var hiPrice: Double,
    var currentPrice: Double,
    var avgPrice: Double,
    var lowHour: String,
    var hiHour: String,
    var bestLowRange: String,
    var values: MutableList<Values>
)
