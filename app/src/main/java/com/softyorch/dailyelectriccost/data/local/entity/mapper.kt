/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.data.local.entity

import com.softyorch.dailyelectriccost.data.repository.datastore.model.SettingsModel

fun SettingsModel.mapToSettings(): Settings = Settings(
    autoLightDark, manualLightDark, autoColors
)