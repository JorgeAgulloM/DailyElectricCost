package com.softyorch.dailyelectriccost.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.softyorch.dailyelectriccost.core.SendEmail
import com.softyorch.dailyelectriccost.ui.screens.main.MainScreen
import com.softyorch.dailyelectriccost.ui.screens.main.MainViewModel
import com.softyorch.dailyelectriccost.ui.screens.splash.SplashScreen

@Composable
fun AppNavigationManager(navController: NavHostController = rememberNavController(), sendEmail: SendEmail) {
    NavHost(navController = navController, startDestination = AppScreenRoutes.SplashScreen.route) {
        composable(route = AppScreenRoutes.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(route = AppScreenRoutes.MainScreen.route) {
            val viewModel = hiltViewModel<MainViewModel>()
            MainScreen(navController, viewModel, sendEmail)
        }
    }
}