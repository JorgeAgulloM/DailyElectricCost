/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun BackgroundS() {

    val topColor = MaterialTheme.colorScheme.primaryContainer
    val bottomColor = MaterialTheme.colorScheme.background
    val sizeCorners = 100.dp

    Surface(Modifier.fillMaxSize()) {
        Column {
            Box(Modifier.height(220.dp), contentAlignment = Alignment.BottomEnd) {
                Box(Modifier.size(sizeCorners).background(color = bottomColor))
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(
                            color = topColor,
                            shape = MaterialTheme.shapes.extraLarge.copy(
                                topStart = ZeroCornerSize,
                                topEnd = ZeroCornerSize,
                                bottomStart = ZeroCornerSize,
                                bottomEnd = CornerSize(sizeCorners)
                            )
                        )
                )

            }
            Box(Modifier.weight(1f), contentAlignment = Alignment.TopStart) {
                Box(Modifier.size(sizeCorners).background(color = topColor))
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(
                            color = bottomColor,
                            shape = MaterialTheme.shapes.extraLarge.copy(
                                topStart = CornerSize(sizeCorners),
                                topEnd = ZeroCornerSize,
                                bottomStart = ZeroCornerSize,
                                bottomEnd = ZeroCornerSize
                            )
                        )
                )
            }
        }
    }
}