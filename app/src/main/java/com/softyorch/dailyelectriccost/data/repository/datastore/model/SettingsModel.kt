/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.data.repository.datastore.model

data class SettingsModel(
    val autoLightDark: Boolean,
    val manualLightDark: Boolean,
    val autoColors: Boolean
)