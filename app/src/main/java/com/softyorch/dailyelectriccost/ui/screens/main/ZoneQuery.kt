/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main

sealed class ZoneQuery(val zone: String) {
    object Peninsula : ZoneQuery("peninsular")
    object Baleares : ZoneQuery("baleares")
    object Canarias : ZoneQuery("canarias")
    object Ceuta : ZoneQuery("ceuta")
    object Melilla : ZoneQuery("melilla")

    companion object {
        val listOfZones = listOf(
            Peninsula, Baleares, Canarias, Ceuta, Melilla
        )
    }
}