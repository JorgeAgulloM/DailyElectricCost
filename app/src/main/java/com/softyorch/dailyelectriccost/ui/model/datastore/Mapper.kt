/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.model.datastore

import com.softyorch.dailyelectriccost.domain.dsUseCases.model.SettingsDomain

fun SettingsDomain.mapToSettingsUi(): SettingsUi = SettingsUi(
    autoLightDark, manualLightDark, autoColors
)