/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.softyorch.dailyelectriccost.utils.funcExtensions.limitLengthToString

@Composable
fun LitlePrice(
    price: Double,
    text: String,
    color: Color
) {
    Column(
        modifier = Modifier.padding(top = 8.dp, start = 32.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.Start
    ) {
        AnimatedPrice(
            price = price
        ) { targetCount ->
            Text(
                text = "${targetCount.limitLengthToString()} €",
                color = color,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge.copy(
                    shadow = Shadow(
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        offset = Offset(1F, 2F),
                        blurRadius = 1F
                    )
                )
            )
        }
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodySmall.copy(
                shadow = Shadow(
                    Color.Black.copy(alpha = 0.5f),
                    offset = Offset(1F, 2F),
                    blurRadius = 1F
                )
            )
        )
    }

}