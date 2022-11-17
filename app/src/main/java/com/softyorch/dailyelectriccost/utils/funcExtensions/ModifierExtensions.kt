/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.utils.funcExtensions

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.simpleHorizontalScrollBar(
    state: LazyListState,
    height: Float = 12f,
    color: Color = Color.LightGray
): Modifier = composed {
    drawWithContent {
        drawContent()

        val firstVisibleElementIndex = state.layoutInfo.visibleItemsInfo.firstOrNull()?.index

        if (firstVisibleElementIndex != null) {

            val scrollableItems =
                state.layoutInfo.totalItemsCount - state.layoutInfo.visibleItemsInfo.size
            val scrollBarWidth = this.size.width / scrollableItems
            val offsetX =
                ((this.size.width - scrollBarWidth) * firstVisibleElementIndex) / scrollableItems

            /*drawRect(
                color = backgroundColor,
                topLeft = Offset(x = 0f, y = this.size.height),
                size = Size(this.size.width, height),
                alpha = 0.1f
            )*/

            drawRoundRect(
                color = color,
                topLeft = Offset(x = offsetX, y = this.size.height),
                size = Size(scrollBarWidth, height),
                cornerRadius = CornerRadius(10f,10f),
                alpha = 0.4f
            )
        }
    }
}