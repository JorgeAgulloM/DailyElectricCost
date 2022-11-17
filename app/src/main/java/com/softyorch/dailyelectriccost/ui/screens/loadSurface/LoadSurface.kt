/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.loadSurface

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.softyorch.dailyelectriccost.core.SendEmail
import com.softyorch.dailyelectriccost.ui.model.datastore.SettingsUi
import com.softyorch.dailyelectriccost.ui.navigation.AppNavigationManager
import com.softyorch.dailyelectriccost.ui.theme.DailyElectricCostTheme

@Composable
fun DailyElectricCost(sendEmail: SendEmail) {

    val viewModel = hiltViewModel<LoadSurfaceViewModel>()
    val settings: SettingsUi by viewModel.settings.observeAsState(
        SettingsUi(
            autoLightDark = false,
            manualLightDark = false,
            autoColors = false
        )
    )



    DailyElectricCostTheme(
        darkTheme = if (settings.autoLightDark) isSystemInDarkTheme() else
            settings.manualLightDark,
        dynamicColor = settings.autoColors
    ) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AppNavigationManager(sendEmail = sendEmail)
        }
    }
}