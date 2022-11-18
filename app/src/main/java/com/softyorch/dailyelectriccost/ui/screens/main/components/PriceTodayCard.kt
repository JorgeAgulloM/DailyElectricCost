/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main.components

import androidx.compose.animation.*
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.softyorch.dailyelectriccost.ui.model.markets.MarketsModelUi
import com.softyorch.dailyelectriccost.ui.screens.main.utils.CalculateColor
import com.softyorch.dailyelectriccost.ui.screens.main.utils.calculateBrush

@Composable
fun CirclePrice(
    marketsData: MarketsModelUi,
    size: Int = 150,
    textSize: TextUnit = 10.sp
) {
    var text by remember { mutableStateOf("") }
    var showPrice by remember { mutableStateOf(0.0) }
    var isAvgPrice by remember { mutableStateOf(false) }
    var progress by remember { mutableStateOf(0f) }

    text = if (isAvgPrice) "Precio medio €/Kwh" else "Precio actual €/Kwh"
    showPrice = if (isAvgPrice) marketsData.avgPrice else marketsData.currentPrice
    progress = ((showPrice * 100) / marketsData.hiPrice).toInt().toFloat()

    val increaseTo = 120
    val increasedSize = (increaseTo * size) / 100
    val shadow = Shadow(
        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
        offset = Offset(1F, 1F),
        blurRadius = 1F
    )
    val brush = calculateBrush(marketsData, showPrice)
    val textColor = CalculateColor(marketsData, showPrice)

    Box(
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.size(size.dp).shadow(
                elevation = 24.dp,
                shape = CircleShape,
                clip = true,
                spotColor = MaterialTheme.colorScheme.primaryContainer
            )
        )
        Box(
            modifier = Modifier.size(increasedSize.dp).padding(8.dp).background(
                color = MaterialTheme.colorScheme.background.copy(alpha = 0.4f),
                shape = CircleShape
            ),
            contentAlignment = Alignment.Center
        ) {

            var isEnable by remember { mutableStateOf(value = false) }
            isEnable = circularProgressBar(brush, size = size.dp, dataUsage = progress)
            Box(
                modifier = Modifier.size(size.dp).padding(8.dp).background(
                    color = MaterialTheme.colorScheme.background,
                    shape = CircleShape
                ),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    modifier = Modifier.fillMaxSize(),
                    enabled = isEnable,
                    onClick = {
                        isAvgPrice = !isAvgPrice
                    }
                ) {
                    LitleKwhPrice(showPrice, text, textSize, textColor, shadow)
                }
            }
        }
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun circularProgressBar(
    brush: Brush,
    size: Dp = 150.dp,
    shadowColor: Color = MaterialTheme.colorScheme.primaryContainer,
    indicatorThickness: Dp = 8.dp,
    dataUsage: Float
): Boolean {

    var isAnimatingFinished by remember { mutableStateOf(false) }

    AnimatedContent(
        targetState = dataUsage,
    ) { targetState ->
        val scale = remember { Animatable(0f) }
        if (targetState > 0) LaunchedEffect(
            key1 = true,
            block = {
                scale.animateTo(
                    targetValue = targetState,
                    animationSpec = spring(dampingRatio = 1f, stiffness = 10f)
                )
            }
        )
        isAnimatingFinished = scale.value == dataUsage

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
    return isAnimatingFinished
}