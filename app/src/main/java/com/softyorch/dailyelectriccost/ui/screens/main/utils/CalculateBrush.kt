/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main.utils

import androidx.compose.ui.graphics.Brush
import com.softyorch.dailyelectriccost.ui.model.markets.MarketsModelUi
import com.softyorch.dailyelectriccost.ui.theme.colorAvg
import com.softyorch.dailyelectriccost.ui.theme.colorHi
import com.softyorch.dailyelectriccost.ui.theme.colorLow

fun calculateBrush(marketsData: MarketsModelUi, value: Double): Brush {
    val listColorHi = listOf(
        colorHi.copy(alpha = 0.9f),
        colorHi.copy(alpha = 0.3f),
        colorHi.copy(alpha = 0.9f),
        colorHi.copy(alpha = 0.3f)
    )

    val listColorAvg = listOf(
        colorAvg.copy(alpha = 0.9f),
        colorAvg.copy(alpha = 0.3f),
        colorAvg.copy(alpha = 0.9f),
        colorAvg.copy(alpha = 0.3f)
    )

    val listColorLow = listOf(
        colorLow.copy(alpha = 0.9f),
        colorLow.copy(alpha = 0.3f),
        colorLow.copy(alpha = 0.9f),
        colorLow.copy(alpha = 0.3f)
    )

    val result: Brush = Brush.linearGradient(
        if (value > ((marketsData.hiPrice / 10) * 8)) {
            listColorHi
        } else if (value > (marketsData.avgPrice / 10) * 9) {
            listColorAvg
        } else listColorLow
    )

    return result
}