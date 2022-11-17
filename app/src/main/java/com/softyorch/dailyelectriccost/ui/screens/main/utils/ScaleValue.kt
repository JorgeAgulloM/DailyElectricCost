/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main.utils

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun scaleValue(
    value: Double,
    scale: Animatable<Float, AnimationVector1D>
): Animatable<Float, AnimationVector1D> {
    LaunchedEffect(
        key1 = true,
        block = {
            scale.animateTo(
                targetValue = value.toFloat(),
                animationSpec = spring(dampingRatio = 1f, stiffness = 10f)
            )
        }
    )
    return scale
}