/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.softyorch.dailyelectriccost.utils.funcExtensions.limitLengthToString

@Composable
fun TextPrice(
    price: Double,
    text: String,
    color: Color,
    shadow: Shadow
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(start = 8.dp, top = 8.dp),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
        AnimatedText(
            price = price
        ) { targetCount ->
            Text(
                text = "${(targetCount / 1000).limitLengthToString()} €",
                color = color,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                    shadow = shadow
                )
            )
        }
    }
}