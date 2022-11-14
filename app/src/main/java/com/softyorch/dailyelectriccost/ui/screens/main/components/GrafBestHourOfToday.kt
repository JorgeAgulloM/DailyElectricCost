/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.softyorch.dailyelectriccost.ui.model.markets.MarketsModelUi
import com.softyorch.dailyelectriccost.ui.theme.colorHi
import com.softyorch.dailyelectriccost.ui.theme.colorLow

@Composable
fun GrafBestHourOfToday(
    modifier: Modifier,
    marketsData: MarketsModelUi
) {
    val shadow = Shadow(
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
        offset = Offset(1f, 2f),
        blurRadius = 2f
    )
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(2.dp)
    ) {
        Column(modifier = modifier.padding(start = 8.dp)) {
            TextPrice(
                marketsData.lowPrice,
                "La hora más económica es a las ${marketsData.lowHour} am:",
                colorLow,
                shadow
            )
            Divider(modifier = Modifier.padding(end = 8.dp))

            TextContent(text = marketsData.bestLowRange)
            Divider(modifier = Modifier.padding(end = 8.dp))

            TextPrice(
                marketsData.hiPrice,
                "La hora más cara de hoy es a las ${marketsData.hiHour} pm:",
                colorHi,
                shadow
            )
            Divider(modifier = Modifier.padding(end = 8.dp))
            Spacer(modifier = Modifier.padding(bottom = 8.dp))
        }
    }
}

@Composable
private fun TextContent(
    text: String
) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth().padding(start = 8.dp, top = 4.dp),
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
    )
}