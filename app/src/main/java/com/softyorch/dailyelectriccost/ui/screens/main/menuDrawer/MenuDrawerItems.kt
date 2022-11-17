/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main.menuDrawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.GetApp
import androidx.compose.ui.graphics.vector.ImageVector

data class MenuDrawerItems(
    val id: Int,
    val text: String,
    val contentDescription: String,
    val icon: ImageVector
) {
    companion object {
        val itemList: List<MenuDrawerItems> =
            listOf(
                MenuDrawerItems(
                    id = 0,
                    text = "DailyElectricCost",
                    contentDescription = "Nombre de la aplicación",
                    icon = Icons.Rounded.GetApp
                ),
                MenuDrawerItems(
                    id = 1,
                    text = "Contacta con el desarrollador",
                    contentDescription = "Envia eMail al desarrollador",
                    icon = Icons.Rounded.Email
                )
            )
    }
}