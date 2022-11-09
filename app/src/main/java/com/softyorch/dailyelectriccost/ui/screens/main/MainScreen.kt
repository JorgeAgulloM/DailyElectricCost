package com.softyorch.dailyelectriccost.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.softyorch.dailyelectriccost.R
import com.softyorch.dailyelectriccost.ui.model.markets.MarketsModelUi
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

    val modifier = Modifier
        .padding(end = 16.dp)
        .shadow(
            elevation = 2.dp, clip = true,
            shape = MaterialTheme.shapes.extraLarge.copy(
                topStart = CornerSize(25.dp)
            ),
            ambientColor = Color.White,
            spotColor = Color.White,
        ).background(
            color = MaterialTheme.colorScheme.background.copy(alpha = 0.95f),
            shape = MaterialTheme.shapes.extraLarge.copy(
                topStart = CornerSize(25.dp)
            )
        )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = it.calculateTopPadding() + 110.dp)
    ) {
        //BackgroundImageMain()

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
                PriceTodayCard(modifier, marketsData)
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                WeekResume(modifier, marketsData.hiPrice, marketsData.lowPrice)
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState(0))
                ) {
                    WeekResume(modifier, marketsData.hiPrice, marketsData.lowPrice)
                    WeekResume(modifier, marketsData.hiPrice, marketsData.lowPrice)
                    Spacer(modifier = Modifier.padding(horizontal = 16.dp))
                }
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                GoogleAddsMain(modifier.padding(end = 16.dp))
            }
        }
    }
}

@Composable
private fun BackgroundImageMain() {
    Image(
        painter = painterResource(id = R.drawable.background_main),
        contentDescription = "MainPrincipal",
        modifier = Modifier
            .fillMaxSize()
            .shadow(
                4.dp,
                shape = MaterialTheme.shapes.extraLarge.copy(
                    bottomStart = ZeroCornerSize,
                    bottomEnd = ZeroCornerSize
                ),
                spotColor = MaterialTheme.colorScheme.primary
            ),
        contentScale = ContentScale.Crop
    )
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
            LitlePrice(marketsData.hiPrice, "máximo", MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f))
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            LitlePrice(marketsData.lowPrice, "mínimo", MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f))
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
    color: Color
) {
    Row(
        modifier = Modifier.padding(top = 4.dp, start = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Precio $text",
            modifier = Modifier.width(100.dp).fillMaxWidth(),
            color = color,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodySmall.copy(
                shadow = Shadow(
                    MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                    offset = Offset(2F, 8F),
                    blurRadius = 4F
                )
            )
        )
        AnimatedText(
            price = price / 1000
        ) { targetCount ->
            Text(
                text = "$targetCount €",
                modifier = Modifier.fillMaxWidth(),
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

    }
}

@Composable
fun PriceTodayCard(
    modifier: Modifier,
    marketsData: MarketsModelUi
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
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.labelLarge
        )
        LitleKwhPrice(marketsData.hiPrice, "máximo", MaterialTheme.colorScheme.error)
        LitleKwhPrice(marketsData.currentPrice, "actual", MaterialTheme.colorScheme.tertiary)
        LitleKwhPrice(marketsData.lowPrice, "mínimo", MaterialTheme.colorScheme.primary)
    }
}

@Composable
fun PriceCard(
    modifier: Modifier,
    price: Double = 0.0,
    text: String = "Precio máximo",
    color: Color = Color(0xffba1a1a)
) {
    Column(
        modifier = modifier
            .height(height = 100.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp)
                .width(24.dp),
            color = Color.White.copy(alpha = 0.7f),
            style = MaterialTheme.typography.bodyLarge,
        )
        AnimatedText(
            price = price
        ) { targetState ->
            Text(
                text = targetState.toString() + "€",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp),
                textAlign = TextAlign.Start,
                color = color,
                style = TextStyle(
                    fontSize = 45.sp,
                    shadow = Shadow(
                        Color.White,
                        offset = Offset(2F, 2F),
                        blurRadius = 4F
                    )
                )
            )
        }
    }
}

@Composable
fun WeekResume(
    modifier: Modifier,
    priceMax: Double = 0.0,
    priceMin: Double = 0.0
) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(width = 300.dp)
    ) {
        Column(
            modifier = Modifier
                .height(height = 215.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Resumen de la semana",
                color = Color.White.copy(alpha = 0.8f),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(16.dp)
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
                Column() {
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        GraphicColumnDay()
                        GraphicColumnDay(80, Color(0xffba1a1a))
                        GraphicColumnDay(86, Color(0xffba1a1a))
                        GraphicColumnDay(72, Color(0xffe7d800))
                        GraphicColumnDay(62, Color(0xff2a694f))
                        GraphicColumnDay(37, Color(0xffaff1cf))
                        GraphicColumnDay(43, Color(0xffaff1cf))
                    }

                    Row() {
                        GraphicTextDay("L")
                        GraphicTextDay("M")
                        GraphicTextDay("X")
                        GraphicTextDay("J")
                        GraphicTextDay("V")
                        GraphicTextDay("S")
                        GraphicTextDay("D")
                    }
                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AnimatedText(
                        price = priceMax
                    ) { targetState ->
                        Text(
                            text = targetState.toString() + "€",
                            color = Color(0xffba1a1a),
                            lineHeight = 16.sp,
                            style = MaterialTheme.typography.bodySmall.copy(
                                shadow = Shadow(
                                    Color.White,
                                    offset = Offset(1F, 1F),
                                    blurRadius = 1F
                                )
                            ),
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, top = 16.dp)
                        )
                    }
                    AnimatedText(
                        price = priceMin
                    ) { targetState ->
                        Text(
                            text = targetState.toString() + "€",
                            color = Color(0xffaff1cf),
                            lineHeight = 16.sp,
                            style = MaterialTheme.typography.bodySmall.copy(
                                shadow = Shadow(
                                    Color.White,
                                    offset = Offset(1F, 1F),
                                    blurRadius = 1F
                                )
                            ),
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                                .padding(start = 8.dp, bottom = 40.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun GraphicColumnDay(
    height: Int = 96,
    color: Color = Color(0xffba1a1a)
) {
    Box(
        modifier = Modifier
            .padding(12.dp)
            .width(width = 8.dp)
            .height(height = height.dp)
            .background(
                color = color,
                shape = MaterialTheme.shapes.large
            )
    )
}

@Composable
private fun GraphicTextDay(
    text: String = "L"
) {
    Text(
        text = text,
        color = Color.White,
        textAlign = TextAlign.Center,
        lineHeight = 24.sp,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .padding(8.dp)
            .width(width = 16.dp)
            .height(height = 17.dp)
    )
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
