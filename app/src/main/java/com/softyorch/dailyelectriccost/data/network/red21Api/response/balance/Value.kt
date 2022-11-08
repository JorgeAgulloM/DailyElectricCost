/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.data.network.red21Api.response.balance

import com.google.gson.annotations.SerializedName

data class Value(
    @SerializedName("datetime") val datetime: String,
    @SerializedName("percentage") val percentage: Double,
    @SerializedName("value") val value: Double
)