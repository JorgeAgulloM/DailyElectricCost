/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main.components

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.softyorch.dailyelectriccost.ui.model.markets.MarketsModelUi
import com.softyorch.dailyelectriccost.ui.screens.main.utils.CalculateColor
import com.softyorch.dailyelectriccost.ui.screens.main.utils.calculateBrush
import com.softyorch.dailyelectriccost.ui.screens.main.utils.scaleValue
import com.softyorch.dailyelectriccost.ui.theme.colorAvg
import com.softyorch.dailyelectriccost.ui.theme.colorHi
import com.softyorch.dailyelectriccost.ui.theme.colorLow
import com.softyorch.dailyelectriccost.utils.Constants.RED21

@Composable
fun PriceTodayCard(
    modifier: Modifier,
    marketsData: MarketsModelUi,
    shadow: Shadow
) {
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(2.dp)
    ) {
        Column(
            modifier = modifier.height(height = 100.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Precios en €/Kwh de hoy",
                modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.labelLarge
            )
            LitleKwhPrice(marketsData.hiPrice, "máximo", colorHi, shadow)
            LitleKwhPrice(marketsData.currentPrice, "actual", colorAvg, shadow)
            LitleKwhPrice(marketsData.lowPrice, "mínimo", colorLow, shadow)
        }
    }
}

@Composable
fun CircleTodayPrice(marketsData: MarketsModelUi, shadow: Shadow) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

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
            Log.d(RED21, "marketsData.hiPrice -> ${marketsData.hiPrice}")
            Log.d(RED21, "marketsData.currentPrice -> ${marketsData.currentPrice}")
            Log.d(RED21, "progress -> $progress")
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
                LitleKwhPrice(marketsData.currentPrice, "actual €/Kw", textColor, shadow)
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