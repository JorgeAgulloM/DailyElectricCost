package com.softyorch.dailyelectriccost.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.softyorch.dailyelectriccost.R
import com.softyorch.dailyelectriccost.core.SendEmail
import com.softyorch.dailyelectriccost.ui.model.markets.MarketsModelUi
import com.softyorch.dailyelectriccost.ui.screens.main.components.*
import com.softyorch.dailyelectriccost.ui.screens.main.menuDrawer.MenuDrawerBody
import com.softyorch.dailyelectriccost.ui.screens.main.menuDrawer.MenuDrawerItems
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel, sendEmail: SendEmail) {

    val marketsData: MarketsModelUi by viewModel.marketsData.observeAsState(
        initial = MarketsModelUi.emptyMarketsDao
    )

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var isDrawerOpen by remember { mutableStateOf(value = false) }
    if (drawerState.isClosed) isDrawerOpen = false

    val cardBrush: Brush = Brush.radialGradient(
        colors = listOf(
            MaterialTheme.colorScheme.onBackground.copy(0.01f),
            MaterialTheme.colorScheme.background.copy(0.9f),
            MaterialTheme.colorScheme.onBackground.copy(0.01f),
            MaterialTheme.colorScheme.background.copy(0.9f),
            MaterialTheme.colorScheme.onBackground.copy(0.01f),
            MaterialTheme.colorScheme.background.copy(0.9f),
        ),
        center = Offset(-25f, 25f),
        radius = 1000f
    )
    val modifier = Modifier.background(brush = cardBrush)

    Scaffold(
        topBar = {
            TopBar {
                scope.launch {
                    isDrawerOpen = !isDrawerOpen
                    if (drawerState.isClosed) drawerState.open() else drawerState.close()
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Head(marketsData)
            Column(
                modifier = Modifier.fillMaxSize().padding(top = it.calculateTopPadding()),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Body(modifier, marketsData)
                Footer(modifier)
            }
        }
        if (isDrawerOpen || drawerState.isOpen)
            MenuDrawerBody(MenuDrawerItems.itemList, it, scope, drawerState, sendEmail)
    }
}

@Composable
fun Head(marketsData: MarketsModelUi) {
    ActualPrice(marketsData)
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Body(
    modifier: Modifier,
    marketsData: MarketsModelUi
) {
    val shadow = Shadow(
        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f),
        offset = Offset(1F, 1F),
        blurRadius = 1F
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState(0))
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            /*Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                PriceTodayCard(modifier, marketsData, shadow)
                MainDatePicker(date){ onClickDatePicker(it) }
            }*/
            PriceTodayCard(modifier, marketsData, shadow)
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            GrafValuesOfToday(
                modifier,
                "Precios en €/Kwh del día",
                marketsData
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            GrafBestHourOfToday(modifier, marketsData)
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
        }
    }

}

@Composable
fun Footer(
    modifier: Modifier
) {
    /** Google Adds */
    ElevatedCard(
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.elevatedCardElevation(2.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(height = 100.dp),
            contentAlignment = Alignment.Center
        ) { Text(text = "Google Adds") }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
private fun TopBar(
    onNavigationIconClick: () -> Unit
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.app_name),//marketsData.title,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.labelLarge.copy(
                        textAlign = TextAlign.Center
                    )
                )
            }
        },
        modifier = Modifier.height(35.dp).padding(top = 8.dp),
        navigationIcon = {
            IconButton(
                onClick = {
                    onNavigationIconClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
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
            var expanded by remember { mutableStateOf(value = false) }
            IconButton(
                onClick = {
                    expanded = true
                }
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
                /** no se puede utilizar ya que la API devuelve
                 * los mismos valores utilizando cualquier filtrado en este ambito
                 * */
                /** no se puede utilizar ya que la API devuelve
                 * los mismos valores utilizando cualquier filtrado en este ambito
                 * */
                /** no se puede utilizar ya que la API devuelve
                 * los mismos valores utilizando cualquier filtrado en este ambito
                 * */
                /** no se puede utilizar ya que la API devuelve
                 * los mismos valores utilizando cualquier filtrado en este ambito
                 * */
                /*SelectZone(expanded, loadDataFrom) {
                    expanded = false
                }*/
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Transparent
        )
    )

}
