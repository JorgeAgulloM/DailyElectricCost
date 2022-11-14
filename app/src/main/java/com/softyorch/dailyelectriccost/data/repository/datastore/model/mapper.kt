/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.data.repository.datastore.model

import com.softyorch.dailyelectriccost.data.local.entity.Settings
import com.softyorch.dailyelectriccost.domain.dsUseCases.model.SettingsDomain

fun Settings.mapToSettingsModel(): SettingsModel = SettingsModel(
    autoLightDark, manualLightDark, autoColors
)

fun SettingsDomain.mapToSettingsModel(): SettingsModel = SettingsModel(
    autoLightDark, manualLightDark, autoColors
)