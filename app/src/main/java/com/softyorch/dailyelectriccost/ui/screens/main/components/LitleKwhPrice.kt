/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.softyorch.dailyelectriccost.utils.funcExtensions.limitLengthToString

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LitleKwhPrice(
    price: Double,
    text: String,
    fontSize: TextUnit,
    color: Color,
    shadow: Shadow
) {
    Column(
        modifier = Modifier.padding(4.dp).width(110.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedContent(
            targetState = text
        ) { textResult ->
            Text(
                text = textResult,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = fontSize
                )
            )
        }
        AnimatedPrice(
            price = price
        ) { targetCount ->
            Text(
                text = "${(targetCount / 1000).limitLengthToString()}€",
                color = color,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = fontSize * 2,
                    shadow = shadow
                )
            )
        }
    }
}