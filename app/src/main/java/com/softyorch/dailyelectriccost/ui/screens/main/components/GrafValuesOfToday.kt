/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.softyorch.dailyelectriccost.ui.model.markets.MarketsModelUi
import com.softyorch.dailyelectriccost.ui.screens.main.utils.calculateBrush
import com.softyorch.dailyelectriccost.ui.screens.main.utils.scaleValue
import com.softyorch.dailyelectriccost.utils.funcExtensions.getHourOfDate
import com.softyorch.dailyelectriccost.utils.funcExtensions.limitLengthToString
import com.softyorch.dailyelectriccost.utils.funcExtensions.simpleHorizontalScrollBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GrafValuesOfToday(
    marketsData: MarketsModelUi
) {
    val height = marketsData.hiPrice

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.height(height = height.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Start
            ) {
                val scrollState = rememberLazyListState()

                LazyRow(
                    modifier = Modifier.simpleHorizontalScrollBar(scrollState),
                    state = scrollState,
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(marketsData.values) { valuesUi ->
                        if (valuesUi.value > 0.0) Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            val brush = calculateBrush(marketsData, valuesUi.value)
                            GraphicColumnDay(
                                value = valuesUi.value,
                                hour = valuesUi.dateTime,
                                isCurrentHour = valuesUi.value == marketsData.currentPrice,
                                maxHeight = height,
                                brush = brush
                            )
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
    value: Double,
    hour: String,
    isCurrentHour: Boolean,
    maxHeight: Double,
    brush: Brush
) {
    val calculateHeight = ((value / 5) * 3).toFloat()
    val calcMaxHeight = ((maxHeight / 5) * 3).toFloat()
    val scale = remember { Animatable(0f) }
    var showPopup by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val modifier = Modifier.width(width = if (isCurrentHour) 16.dp else 4.dp).padding(bottom = 2.dp)

    LaunchedEffect(key1 = true,
        block = {
            scale.animateTo(
                targetValue = calculateHeight,
                animationSpec = spring(dampingRatio = 1f, stiffness = 10f)
            )
        }
    )
    Box(
        modifier = Modifier.padding(top = 16.dp).clickable {
            showPopup = true
            scope.launch {
                delay(2000)
                showPopup = false
            }
        },
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
    if (showPopup) {
        Popup(
            properties = PopupProperties(focusable = true)
        ) {
            Column(
                modifier = Modifier
                    .shadow(4.dp, shape = MaterialTheme.shapes.medium)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                Text(
                    text = "Hora: ${hour.getHourOfDate()}",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "Precio: ${(value / 1000).limitLengthToString()}€",
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 4.dp),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
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




















