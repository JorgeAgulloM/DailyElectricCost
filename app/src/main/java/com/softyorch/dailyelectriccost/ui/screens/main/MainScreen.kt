package com.softyorch.dailyelectriccost.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.softyorch.dailyelectriccost.ui.model.markets.MarketsModelUi
import com.softyorch.dailyelectriccost.ui.theme.colorAvg
import com.softyorch.dailyelectriccost.ui.theme.colorHi
import com.softyorch.dailyelectriccost.ui.theme.colorLow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {

    val marketsData: MarketsModelUi by viewModel.marketsData.observeAsState(
        initial = MarketsModelUi.emptyMarketsDao
    )
    //val geoLimit: String by viewModel.geoLimit.observeAsState(initial = EMPTY_STRING)

    Scaffold(
        topBar = {
            TopBar(marketsData, navController)
        },
        containerColor = MaterialTheme.colorScheme.tertiaryContainer
    ) {
        Head(marketsData)
        Body(navController, marketsData, it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(marketsData: MarketsModelUi, navController: NavController) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = marketsData.title,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        },
        modifier = Modifier.height(35.dp).padding(top = 8.dp),
        navigationIcon = {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = MaterialTheme.shapes.small
                        ),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }
        },
        actions = {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreHoriz,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = MaterialTheme.shapes.small
                        ),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@Composable
fun Head(marketsData: MarketsModelUi) {
    ActualPrice(marketsData)
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Body(navController: NavController, marketsData: MarketsModelUi, it: PaddingValues) {

    val bodyBrush: Brush = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.background.copy(1f),
            MaterialTheme.colorScheme.background.copy(0.95f),
            MaterialTheme.colorScheme.background.copy(1f),
            MaterialTheme.colorScheme.background.copy(0.95f),
            MaterialTheme.colorScheme.background.copy(1f),
            MaterialTheme.colorScheme.background.copy(0.95f),
            MaterialTheme.colorScheme.background.copy(1f),
            MaterialTheme.colorScheme.background.copy(0.95f),
            MaterialTheme.colorScheme.background.copy(1f),
            MaterialTheme.colorScheme.background.copy(0.95f)
        )
    )

    val cardBrush: Brush = Brush.radialGradient(
        colors = listOf(
            MaterialTheme.colorScheme.onBackground.copy(0.1f),
            MaterialTheme.colorScheme.background.copy(0.9f)
        ),
        radius = 600f
    )

    val shadow = Shadow(
        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
        offset = Offset(1F, 1F),
        blurRadius = 1F
    )

    val modifier = Modifier
        .padding(end = 16.dp)
        .shadow(
            elevation = 2.dp, clip = true,
            shape = MaterialTheme.shapes.extraLarge.copy(
                topStart = CornerSize(26.dp)
            ),
            ambientColor = Color.White,
            spotColor = Color.White,
        ).background(
            brush = cardBrush,
            shape = MaterialTheme.shapes.extraLarge.copy(
                topStart = CornerSize(26.dp)
            )
        )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = it.calculateTopPadding() + 110.dp)
    ) {
        val bgrShape = MaterialTheme.shapes.extraLarge.copy(
            bottomStart = ZeroCornerSize,
            bottomEnd = ZeroCornerSize
        )
        Box(
            modifier = Modifier.background(
                color = MaterialTheme.colorScheme.onBackground,
                shape = bgrShape
            )
        ) {
            Column(
                modifier = Modifier
                    .background(brush = bodyBrush, shape = bgrShape)
                    .fillMaxSize()
                    .padding(start = 16.dp, top = 16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                PriceTodayCard(modifier, marketsData, shadow)
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                GrafToday(
                    modifier,
                    "Preio en Kwh del día",
                    marketsData,
                    marketsData.hiPrice,
                    marketsData.lowPrice
                )
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                /*Row(
                    modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState(0))
                ) {
                    GrafLastSevenDays(modifier, "Precios mínimos" ,marketsData, marketsData.hiPrice, marketsData.lowPrice)
                    GrafLastSevenDays(modifier,"Precios máximos" ,marketsData, marketsData.hiPrice, marketsData.lowPrice)
                    Spacer(modifier = Modifier.padding(horizontal = 16.dp))
                }*/
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                GoogleAddsMain(modifier.padding(end = 16.dp))
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedText(
    price: Double = 0.0,
    animatedScope: @Composable (Double) -> Unit
) {
    val scope = rememberCoroutineScope()
    var animatedPrice by remember { mutableStateOf(0.0) }
    scope.launch {
        while (animatedPrice < price) {
            delay(200)
            animatedPrice++
        }
        animatedPrice = price
    }
    AnimatedContent(
        targetState = animatedPrice,
        transitionSpec = {
            if (animatedPrice > 0.0) {
                slideInVertically { height -> height } + fadeIn() with
                        slideOutVertically { height -> -height } + fadeOut()
            } else {
                slideInVertically { height -> -height } + fadeIn() with
                        slideOutVertically { height -> height } + fadeOut()
            }.using(
                SizeTransform(clip = false)
            )
        }
    ) { targetState ->
        animatedScope(targetState)
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ActualPrice(
    marketsData: MarketsModelUi
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 48.dp)
            .width(width = 203.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            AnimatedText(
                price = marketsData.currentPrice
            ) { targetCount ->
                Text(
                    text = "$targetCount €",
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.End,
                    style = TextStyle(
                        fontSize = 45.sp,
                        shadow = Shadow(
                            MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                            offset = Offset(2F, 10F),
                            blurRadius = 8F
                        )
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            Text(
                text = "${marketsData.type} actual",
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodyMedium.copy(
                    shadow = Shadow(
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        offset = Offset(2F, 8F),
                        blurRadius = 4F
                    )
                )
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start
        ) {
            LitlePrice(
                marketsData.hiPrice,
                "máximo",
                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f)
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            LitlePrice(
                marketsData.lowPrice,
                "mínimo",
                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f)
            )
        }
    }
}

@Composable
private fun LitlePrice(
    price: Double,
    text: String,
    color: Color
) {
    Column(
        modifier = Modifier.padding(top = 8.dp, start = 32.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.Start
    ) {
        AnimatedText(
            price = price
        ) { targetCount ->
            Text(
                text = "$targetCount €",
                color = color,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge.copy(
                    shadow = Shadow(
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        offset = Offset(2F, 8F),
                        blurRadius = 4F
                    )
                )
            )
        }
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodySmall.copy(
                shadow = Shadow(
                    Color.Black.copy(alpha = 0.5f),
                    offset = Offset(2F, 8F),
                    blurRadius = 4F
                )
            )
        )
    }

}

@Composable
private fun LitleKwhPrice(
    price: Double,
    text: String,
    color: Color,
    shadow: Shadow
) {
    Row(
        modifier = Modifier.padding(top = 4.dp, start = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Precio $text",
            modifier = Modifier.width(100.dp),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Bold,
                shadow = shadow
            )
        )
        AnimatedText(
            price = price
        ) { targetCount ->
            Text(
                text = "${targetCount / 1000} €",
                modifier = Modifier.fillMaxWidth(),
                color = color,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                    shadow = shadow
                )
            )
        }
    }
}

@Composable
fun PriceTodayCard(
    modifier: Modifier,
    marketsData: MarketsModelUi,
    shadow: Shadow
) {
    Column(
        modifier = modifier
            .width(width = 300.dp)
            .height(height = 100.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Precios en €/Kwh de hoy",
            modifier = Modifier.padding(start = 16.dp, top = 4.dp).fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.labelLarge
        )
        LitleKwhPrice(marketsData.hiPrice, "máximo", colorHi, shadow)
        LitleKwhPrice(marketsData.currentPrice, "actual", colorAvg, shadow)
        LitleKwhPrice(marketsData.lowPrice, "mínimo", colorLow, shadow)
    }
}

@Composable
fun GrafToday(
    modifier: Modifier,
    title: String,
    marketsData: MarketsModelUi,
    priceMax: Double = 0.0,
    priceMin: Double = 0.0
) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
        //.width(width = 300.dp)
    ) {
        Column(
            modifier = Modifier
                .height(height = 215.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = title,
                color = Color.White.copy(alpha = 0.8f),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(start = 8.dp, bottom = 8.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight().width(70.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
                ) {
                    val shadow = Shadow(
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
                        offset = Offset(1F, 1F),
                        blurRadius = 1F
                    )
                    AnimatedText(
                        price = priceMax
                    ) { targetState ->
                        Text(
                            text = "${targetState / 1000} €",
                            color = colorHi,
                            lineHeight = 16.sp,
                            style = MaterialTheme.typography.bodySmall.copy(shadow = shadow),
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .padding(start = 8.dp, top = 8.dp)
                        )
                    }
                    AnimatedText(
                        price = priceMin
                    ) { targetState ->
                        Text(
                            text = "${targetState / 1000} €",
                            color = colorLow,
                            lineHeight = 16.sp,
                            style = MaterialTheme.typography.bodySmall.copy(shadow = shadow),
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                //.fillMaxWidth()
                                .padding(start = 8.dp, bottom = 32.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState(0)),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {

                    marketsData.values.forEach { valuesUi ->
                        if (valuesUi.value > 0.0) Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            val brush = calculateBrush(marketsData, valuesUi.value)
                            GraphicColumnDay(valuesUi.value.toInt() / 3, brush)
                            GraphicTextDay(valuesUi.value / 1000.0)
                        }
                    }

                }
            }
        }
    }
}

fun calculateBrush(marketsData: MarketsModelUi, value: Double): Brush {
    val listColorHi = listOf(
        colorHi.copy(alpha = 0.9f),
        colorHi.copy(alpha = 0.1f),
        colorHi.copy(alpha = 0.9f),
        colorHi.copy(alpha = 0.1f)
    )

    val listColorAvg = listOf(
        colorAvg.copy(alpha = 0.9f),
        colorAvg.copy(alpha = 0.3f),
        colorAvg.copy(alpha = 0.9f),
        colorAvg.copy(alpha = 0.3f)
    )

    val listColorLow = listOf(
        colorLow.copy(alpha = 0.9f),
        colorLow.copy(alpha = 0.3f),
        colorLow.copy(alpha = 0.9f),
        colorLow.copy(alpha = 0.3f)
    )

    val result: Brush = Brush.linearGradient(
        if (value > ((marketsData.hiPrice / 10) * 8)) {
            listColorHi
        } else if (value > ((marketsData.hiPrice / 10) * 5)) {
            listColorAvg
        } else listColorLow
    )

    return result
}

@Composable
private fun GraphicColumnDay(
    height: Int = 96,
    brush: Brush
) {
    val scale = remember { Animatable(0f) }
    LaunchedEffect(key1 = true,
        block = {
            scale.animateTo(
                targetValue = height.toFloat(),
                animationSpec = spring(dampingRatio = 1f, stiffness = 10f)
            )
        }
    )
    Box(
        modifier = Modifier
            .width(width = 8.dp)
            .height(height = scale.value.dp)
            .background(
                brush = brush,
                shape = MaterialTheme.shapes.large
            )
    )

}

@Composable
private fun GraphicTextDay(
    price: Double
) {
    Box(
        modifier = Modifier.graphicsLayer(
            rotationZ = -65f
        )
    ) {
        val scale = remember { Animatable(0f) }
        val valueScale = scaleValue(price, scale).value

        val text = if (valueScale.toString().length > 6)
            valueScale.toString().substring(0, 5)
        else valueScale.toString()

        Text(
            text = "$text€",
            color = Color.White,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 8.sp
            ),
            modifier = Modifier
                .padding(vertical = 12.dp)
        )
    }
}

@Composable
private fun scaleValue(
    value: Double,
    scale: Animatable<Float, AnimationVector1D>
): Animatable<Float, AnimationVector1D> {
    LaunchedEffect(
        key1 = true,
        block = {
            scale.animateTo(
                targetValue = value.toFloat(),
                animationSpec = spring(dampingRatio = 1f, stiffness = 10f)
            )
        }
    )
    return scale
}

@Composable
private fun GoogleAddsMain(
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height = 100.dp),
        contentAlignment = Alignment.Center
    ) { Text(text = "Google Adds") }
}
