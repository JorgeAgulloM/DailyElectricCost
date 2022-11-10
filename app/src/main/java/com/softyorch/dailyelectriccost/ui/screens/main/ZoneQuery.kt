/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main

sealed interface ZoneQuery {
    object Peninsula : ZoneQuery
    object Baleares : ZoneQuery
    object Canarias : ZoneQuery
    object Ceuta : ZoneQuery
    object Melilla : ZoneQuery
}

enum class EnumZoneQuery(val zone:String) {
    Peninsula("peninsular"),
    Baleares("baleares"),
    Canarias("canarias"),
    Ceuta("ceuta"),
    Melilla("melilla")
}