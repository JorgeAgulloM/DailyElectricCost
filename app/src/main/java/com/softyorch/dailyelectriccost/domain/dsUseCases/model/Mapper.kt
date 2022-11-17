/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.domain.dsUseCases.model

import com.softyorch.dailyelectriccost.data.repository.datastore.model.SettingsModel
import com.softyorch.dailyelectriccost.ui.model.datastore.SettingsUi

fun SettingsModel.mapToSettingsDomain(): SettingsDomain = SettingsDomain(
    autoLightDark, manualLightDark, autoColors
)

fun SettingsUi.mapToSettingsDomain(): SettingsDomain = SettingsDomain(
    autoLightDark, manualLightDark, autoColors
)