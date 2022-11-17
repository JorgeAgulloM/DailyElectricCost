/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.data.network.red21Api.response.generation

import com.google.gson.annotations.SerializedName

data class CacheControl(
    @SerializedName("cache") val cache: String,
    @SerializedName("expireAt") val expireAt: String
)