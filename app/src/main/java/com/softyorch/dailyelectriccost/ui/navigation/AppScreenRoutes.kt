package com.softyorch.dailyelectriccost.ui.navigation

sealed class AppScreenRoutes(val route: String){
    object SplashScreen: AppScreenRoutes(route = AppScreens.SplashScreen.name)
    object MainScreen: AppScreenRoutes(route = AppScreens.MainScreen.name)
}
