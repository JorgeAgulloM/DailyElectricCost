package com.softyorch.dailyelectriccost.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.softyorch.dailyelectriccost.R
import com.softyorch.dailyelectriccost.ui.model.markets.MarketsModelUi
import com.softyorch.dailyelectriccost.utils.Constants.EMPTY_STRING
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {

    Scaffold(
    ) {

/*        val dataDefault: Red21Response? by viewModel.defaultData.observeAsState(
            initial = Red21Response.red21ResponseEmpty
        )
        val included: List<Included?> by viewModel.includes.observeAsState(
            initial = Red21Response.red21ResponseEmpty.included
        )*/

        Body(navController, viewModel, it)
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Body(navController: NavController, viewModel: MainViewModel, it: PaddingValues) {

    val marketsData: MarketsModelUi by viewModel.marketsData.observeAsState(
        initial = MarketsModelUi.emptyMarketsDao
    )
    val geoLimit: String by viewModel.geoLimit.observeAsState(initial = EMPTY_STRING)

    val modifier = Modifier
        .shadow(
            elevation = 2.dp, clip = true,
            shape = MaterialTheme.shapes.extraLarge.copy(
                    topStart = CornerSize(25.dp)
                ),
            ambientColor = Color.White,
            spotColor = Color.White,
        ).background(
            color = Color(0xff131313).copy(alpha = 0.95f),
            shape = MaterialTheme.shapes.extraLarge.copy(
                    topStart = CornerSize(25.dp)
                )
        ).width(width = 300.dp)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = it.calculateTopPadding() + 24.dp)
    ) {
        BackgroundImageMain()

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ActualPrice(marketsData.currentPrice)
            Spacer(modifier = Modifier.padding(vertical = 16.dp))
            PriceCard(modifier, marketsData.hiPrice, "Mejor máximo", Color(0xffba1a1a))
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            PriceCard(modifier, marketsData.lowPrice, "Mejor precio del día", Color(0xffAFF1CF))//AFF1CF
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            WeekResume(modifier, marketsData.hiPrice, marketsData.lowPrice)
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            GoogleAddsMain(modifier)
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
    price: Double = 0.0,
    text: String = "Precio actual"
) {

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .width(width = 203.dp)
    ) {
        AnimatedText(
            price = price
        ) { targetCount ->
            Text(
                text = targetCount.toString() + "€",
                color = Color(0xffE7D800),
                textAlign = TextAlign.Center,
                lineHeight = 52.sp,
                style = TextStyle(
                    fontSize = 45.sp,
                    shadow = Shadow(
                        Color.White,
                        offset = Offset(2F,2F),
                        blurRadius = 4F
                    )
                ),
                modifier = Modifier
                    .width(width = 203.dp)
                    .height(height = 56.dp)
            )
        }

        Text(
            text = text,
            color = Color.White.copy(alpha = 0.8f),
            textAlign = TextAlign.End,
            lineHeight = 24.sp,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .width(width = 163.dp)
                .height(height = 24.dp)
        )
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
                        offset = Offset(2F,2F),
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
                                    offset = Offset(1F,1F),
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
                                    offset = Offset(1F,1F),
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
            .padding(8.dp)
            .width(width = 16.dp)
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
