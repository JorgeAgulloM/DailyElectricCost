/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.unit.dp
import com.softyorch.dailyelectriccost.ui.model.markets.MarketsModelUi
import com.softyorch.dailyelectriccost.ui.theme.colorAvg
import com.softyorch.dailyelectriccost.ui.theme.colorHi
import com.softyorch.dailyelectriccost.ui.theme.colorLow

@Composable
fun GrafBestHourOfToday(marketsData: MarketsModelUi) {
    val shadow = Shadow(
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
        offset = Offset(1f, 2f),
        blurRadius = 2f
    )

    Column {
        TextPrice(
            marketsData.lowPrice,
            "Hora más barata del día:  ${marketsData.lowHour}",
            colorLow,
            shadow
        )
        Spacer(modifier = Modifier.size(8.dp))
        TextPrice(
            marketsData.avgPrice,
            "Precio medio del día",
            colorAvg,
            shadow
        )
        Spacer(modifier = Modifier.size(8.dp))
        TextPrice(
            marketsData.hiPrice,
            "Hora más cara del día:  ${marketsData.hiHour}",
            colorHi,
            shadow
        )
        Spacer(modifier = Modifier.size(8.dp))
        TextContent(text = marketsData.bestLowRange)
    }
}

@Composable
private fun TextContent(
    text: String
) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
        style = MaterialTheme.typography.labelMedium
    )
}