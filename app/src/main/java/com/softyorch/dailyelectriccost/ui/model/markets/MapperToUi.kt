/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.model.markets

import com.softyorch.dailyelectriccost.data.repository.red21Repository.dao.MarketsDao
import com.softyorch.dailyelectriccost.data.repository.red21Repository.dao.Values

fun Values.mapToValuesUi(): ValuesUi = ValuesUi(
    value, dateTime
)


fun MarketsDao.mapToMarketsModelUi(): MarketsModelUi = MarketsModelUi(
    title, lastUpdate, type, lowPrice, hiPrice, currentPrice, values.map{
        it.mapToValuesUi()
    } as MutableList<ValuesUi>
)