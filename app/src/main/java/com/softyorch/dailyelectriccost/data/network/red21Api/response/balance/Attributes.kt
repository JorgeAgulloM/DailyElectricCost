/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.data.network.red21Api.response.balance

import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("description") val description: String,
    @SerializedName("last-update") val lastUpdate: String,
    @SerializedName("title") val title: String
)