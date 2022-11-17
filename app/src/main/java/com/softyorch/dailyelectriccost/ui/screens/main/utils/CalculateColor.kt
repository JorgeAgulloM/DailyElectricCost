/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main.utils

import androidx.compose.ui.graphics.Color
import com.softyorch.dailyelectriccost.ui.model.markets.MarketsModelUi
import com.softyorch.dailyelectriccost.ui.theme.colorAvg
import com.softyorch.dailyelectriccost.ui.theme.colorHi
import com.softyorch.dailyelectriccost.ui.theme.colorLow

fun CalculateColor(marketsData: MarketsModelUi, value: Double): Color =
    if (value > ((marketsData.hiPrice / 10) * 8)) {
        colorHi
    } else if (value > (marketsData.avgPrice / 10) * 9) {
        colorAvg
    } else colorLow