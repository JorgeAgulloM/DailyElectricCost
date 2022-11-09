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
    var values: MutableList<Values>
){
    companion object{
        val emptyMarketsDao: MarketsDao = MarketsDao(
            title = EMPTY_STRING,
            lastUpdate = EMPTY_STRING,
            type = EMPTY_STRING,
            lowPrice = 0.0,
            hiPrice = 0.0,
            currentPrice = 0.0,
            values = mutableListOf(
                Values(
                    value = 0.0,
                    dateTime = EMPTY_STRING
                )
            )
        )
    }
}
