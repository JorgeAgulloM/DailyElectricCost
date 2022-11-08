/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.data.network.red21Api.response.balance

import com.google.gson.annotations.SerializedName

data class Red21Balance(
    @SerializedName("data") val data: Data,
    @SerializedName("included") val included: List<Included>
){
}