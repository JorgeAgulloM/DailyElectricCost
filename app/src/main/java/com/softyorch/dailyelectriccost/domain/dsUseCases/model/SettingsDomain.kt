/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.domain.dsUseCases.model

data class SettingsDomain(
    val autoLightDark: Boolean,
    val manualLightDark: Boolean,
    val autoColors: Boolean
)
