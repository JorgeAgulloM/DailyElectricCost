/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.model.markets

import com.softyorch.dailyelectriccost.utils.Constants

data class MarketsModelUi(
    var title: String,
    var lastUpdate: String,
    var type: String,
    var lowPrice: Double,
    var hiPrice: Double,
    var currentPrice: Double,
    var avgPrice: Double,
    var values: MutableList<ValuesUi>
) {
    companion object{
        val emptyMarketsDao: MarketsModelUi = MarketsModelUi(
            title = Constants.EMPTY_STRING,
            lastUpdate = Constants.EMPTY_STRING,
            type = Constants.EMPTY_STRING,
            lowPrice = 0.0,
            hiPrice = 0.0,
            currentPrice = 0.0,
            avgPrice = 0.0,
            values = mutableListOf(
                ValuesUi(
                    value = 0.0,
                    dateTime = Constants.EMPTY_STRING
                )
            )
        )
    }
}
