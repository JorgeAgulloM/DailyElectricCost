/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.data.network.red21Api.response.market

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("attributes") val attributes: Attributes,
    @SerializedName("id") val id: String,
    @SerializedName("meta") val meta: Meta,
    @SerializedName("type") val type: String
)