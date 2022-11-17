/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.softyorch.dailyelectriccost.ui.model.markets.MarketsModelUi
import com.softyorch.dailyelectriccost.ui.screens.main.utils.CalculateColor
import com.softyorch.dailyelectriccost.ui.screens.main.utils.calculateBrush

@Composable
fun CircleTodayPrice(marketsData: MarketsModelUi) {
    val shadow = Shadow(
        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
        offset = Offset(1F, 1F),
        blurRadius = 1F
    )
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier
            .size(150.dp)
            .shadow(elevation = 24.dp, shape = CircleShape, clip = true, spotColor = MaterialTheme.colorScheme.primaryContainer)
        )
        Box(
            modifier = Modifier
                .size(170.dp)
                .padding(8.dp)
                .background(
                    color = MaterialTheme.colorScheme.background.copy(alpha = 0.4f),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            val progress = ((marketsData.currentPrice * 100) / marketsData.hiPrice).toInt().toFloat()

            CircularProgressBar(marketsData = marketsData, dataUsage = progress)
            val textColor = CalculateColor(marketsData, marketsData.currentPrice)
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .padding(8.dp)
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                LitleKwhPrice(marketsData.currentPrice, "actual €/Kwh", textColor, shadow)
            }
        }
    }
}

@Composable
fun CircularProgressBar(
    marketsData: MarketsModelUi,
    size: Dp = 140.dp,
    shadowColor: Color = MaterialTheme.colorScheme.primaryContainer,
    indicatorThickness: Dp = 8.dp,
    dataUsage: Float
) {

    val brush = calculateBrush(marketsData, marketsData.currentPrice)
    val scale = remember { Animatable(0f) }
    if (dataUsage > 0) LaunchedEffect(
        key1 = true,
        block = {
            scale.animateTo(
                targetValue = dataUsage,
                animationSpec = spring(dampingRatio = 1f, stiffness = 10f)
            )
        }
    )

    Box(
        modifier = Modifier.size(size),
        contentAlignment = Alignment.Center
    ) {

        val backColor = MaterialTheme.colorScheme.background
        Canvas(
            modifier = Modifier.size(size)
        ) {

            // For shadow
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(shadowColor, backColor),
                    center = Offset(x = this.size.width / 2, y = this.size.height / 2),
                    radius = this.size.height / 2
                ),
                radius = this.size.height / 2,
                center = Offset(x = this.size.width / 2, y = this.size.height / 2)
            )

            // Convert the dataUsage to angle
            val sweepAngle = (scale.value) * 360 / 100

            // Foreground indicator
            drawArc(
                brush = brush,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = indicatorThickness.toPx(), cap = StrokeCap.Round),
                size = Size(
                    width = (size - indicatorThickness).toPx(),
                    height = (size - indicatorThickness).toPx()
                ),
                topLeft = Offset(
                    x = (indicatorThickness / 2).toPx(),
                    y = (indicatorThickness / 2).toPx()
                )
            )
        }
    }
}