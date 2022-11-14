/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.data.network.red21Api.response.market

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("cache-control") val cacheControl: CacheControl
)