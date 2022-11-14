package com.softyorch.dailyelectriccost.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.softyorch.dailyelectriccost.core.SendEmail
import com.softyorch.dailyelectriccost.ui.navigation.AppNavigationManager
import com.softyorch.dailyelectriccost.ui.theme.DailyElectricCostTheme

@Composable
fun DailyElectricCost(sendEmail: SendEmail) {
    DailyElectricCostTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AppNavigationManager(sendEmail = sendEmail)
        }
    }
}