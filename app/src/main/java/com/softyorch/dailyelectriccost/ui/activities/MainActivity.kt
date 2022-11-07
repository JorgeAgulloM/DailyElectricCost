package com.softyorch.dailyelectriccost.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.softyorch.dailyelectriccost.ui.screens.DailyElectricCost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<MainViewModel>()
            viewModel.getDataDefault()
            viewModel.getDataGeoTruncate()

            DailyElectricCost()
        }
    }
}