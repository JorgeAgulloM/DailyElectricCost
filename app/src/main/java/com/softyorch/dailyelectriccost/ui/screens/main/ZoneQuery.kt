/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main

sealed class ZoneQuery(val zone: String, val geoId:String) {
    object Peninsula : ZoneQuery("peninsular", "8741")
    object Canarias : ZoneQuery("canarias", "8742")
    object Baleares : ZoneQuery("baleares", "8743")
    object Ceuta : ZoneQuery("ceuta", "8744")
    object Melilla : ZoneQuery("melilla", "8745")

    companion object {
        val listOfZones = listOf(
            Peninsula, Canarias, Baleares, Ceuta, Melilla
        )
    }
}