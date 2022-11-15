/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main.menuDrawer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.softyorch.dailyelectriccost.core.SendEmail
import com.softyorch.dailyelectriccost.ui.model.datastore.SettingsUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDrawerBody(
    settings: SettingsUi,
    items: List<MenuDrawerItems>,
    paddingValues: PaddingValues,
    scope: CoroutineScope,
    drawerState: DrawerState,
    sendEmail: SendEmail,
    onCheckedChange: (SettingsUi) -> Unit
) {
    val selectedItem = remember { mutableStateOf(items[0]) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(paddingValues.calculateTopPadding() + 8.dp))
                Divider(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp))

                SwitchMenuDrawer(
                    settings.autoLightDark,
                    "Modo día/noche automático",
                    iconOn = Icons.Filled.WbSunny,
                    iconOff = Icons.Filled.WbSunny
                ) {
                    onCheckedChange(settings.copy(autoLightDark = it))
                }

                AnimatedVisibility(!settings.autoLightDark) {
                    SwitchMenuDrawer(
                        settings.manualLightDark,
                        "Cambiar tema día/noche",
                        iconOn = Icons.Rounded.WbSunny,
                        iconOff = Icons.Rounded.WbSunny
                    ) {
                        onCheckedChange(settings.copy(manualLightDark = it))
                    }
                }

                SwitchMenuDrawer(
                    settings.autoColors,
                    "Color automático",
                    iconOn = Icons.Rounded.InvertColors,
                    iconOff = Icons.Rounded.InvertColorsOff
                ) {
                    onCheckedChange(settings.copy(autoColors = it))
                }

                Divider(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.icon, contentDescription = item.contentDescription) },
                        label = { Text(text = item.text) },
                        selected = item == selectedItem.value,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                                if (item.id == 1) sendEmail()
                            }
                            selectedItem.value = item
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        content = {}
    )
}

@Composable
private fun SwitchMenuDrawer(
    checked: Boolean,
    text: String,
    iconOn: ImageVector,
    iconOff: ImageVector,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.padding(start = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Switch(
            checked = checked,
            onCheckedChange = { onCheckedChange(it) },
            thumbContent = {
                Icon(imageVector = if (checked) iconOn else iconOff, contentDescription = text)
            },
            colors = SwitchDefaults.colors(
                checkedIconColor = MaterialTheme.colorScheme.secondary,
                checkedTrackColor = MaterialTheme.colorScheme.tertiary
            )
        )
        Text(
            text = text,
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.labelMedium
        )
    }
}