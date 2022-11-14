/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main.menuDrawer

data class SettingsItemsMenu(
    val id: Int,
    val text: String,
    val value: Boolean
) {
    companion object {
        val settings: List<SettingsItemsMenu> = listOf(
            SettingsItemsMenu(
                id = 0, "Modo día/noche automático", value = false
            ),
            SettingsItemsMenu(
                id = 1, "Cambiar tema día/noche", value = false
            ),
            SettingsItemsMenu(
                id = 2, "Color automático", value = false
            )
        )
    }
}
