package com.softyorch.dailyelectriccost.ui.screens.splash


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.softyorch.dailyelectriccost.R
import com.softyorch.dailyelectriccost.ui.navigation.AppScreenRoutes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember { Animatable(0f) }
    LaunchedEffect(key1 = true, block = {
        scale.animateTo(
            targetValue = 2f,
            animationSpec = spring(dampingRatio = 1f, stiffness = 10f)
        )

        delay(200)

        navController.navigate(AppScreenRoutes.MainScreen.route){
            navController.backQueue.clear()
        }
    })
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Image(painter = painterResource(R.drawable.logo), contentDescription = null, modifier = Modifier.scale(scale.value))
    }
}