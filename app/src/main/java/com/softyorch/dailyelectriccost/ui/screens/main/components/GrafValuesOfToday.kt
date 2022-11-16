/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.softyorch.dailyelectriccost.ui.model.markets.MarketsModelUi
import com.softyorch.dailyelectriccost.ui.screens.main.utils.calculateBrush
import com.softyorch.dailyelectriccost.ui.screens.main.utils.scaleValue
import com.softyorch.dailyelectriccost.ui.theme.colorAvg
import com.softyorch.dailyelectriccost.utils.funcExtensions.limitLengthToString

@Composable
fun GrafValuesOfToday(
    //modifier: Modifier,
    title: String,
    marketsData: MarketsModelUi
) {
    val avgPrice = "*Precio medio ${
        (marketsData.avgPrice / 1000).limitLengthToString()
    } €"

    val height = marketsData.hiPrice

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(height = height.dp)
        //.width(width = 300.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(start = 16.dp),
                    textAlign = TextAlign.Start
                )
                Text(
                    text = avgPrice,
                    color = colorAvg.copy(alpha = 0.9f),
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(top = 8.dp),
                    textAlign = TextAlign.Start
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Start
            ) {
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState(0)),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {
                    marketsData.values.forEach { valuesUi ->
                        if (valuesUi.value > 0.0) Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            val brush = calculateBrush(marketsData, valuesUi.value)
                            GraphicColumnDay(valuesUi.value.toInt(), height, brush)
                            GraphicTextDay(valuesUi.value)
                        }
                    }
                }
            }
        }
    }

}


@Composable
private fun GraphicColumnDay(
    height: Int = 96,
    maxHeight: Double,
    brush: Brush
) {
    val calculateHeight = ((height / 5) * 3).toFloat()
    val calcMaxHeight = ((maxHeight / 5) * 3).toFloat()
    val scale = remember { Animatable(0f) }
    val modifier = Modifier.width(width = 4.dp).padding(bottom = 2.dp)

    LaunchedEffect(key1 = true,
        block = {
            scale.animateTo(
                targetValue = calculateHeight,
                animationSpec = spring(dampingRatio = 1f, stiffness = 10f)
            )
        }
    )
    Box(
        modifier = Modifier.padding(top = 16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = modifier
                .height(height = calcMaxHeight.dp)
                .background(brush = brush, shape = MaterialTheme.shapes.large, alpha = 0.2f)
        )
        Box(
            modifier = modifier
                .height(height = scale.value.dp)
                .background(brush = brush, shape = MaterialTheme.shapes.large, alpha = 0.9f)
        )
    }
}

@Composable
private fun GraphicTextDay(
    price: Double
) {
    Box(
        modifier = Modifier
            .graphicsLayer(
                rotationZ = -65f
            )
            .padding(vertical = 10.dp)
            .height(20.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        val calculatePrice = price / 1000
        val scale = remember { Animatable(0f) }
        val valueScale = scaleValue(calculatePrice, scale).value

        Text(
            text = "${valueScale.limitLengthToString()}€",
            modifier = Modifier.height(20.dp),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 8.sp
            )
        )
    }
}