/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.data.network.red21Api.response.generation

import com.google.gson.annotations.SerializedName

data class AttributesX(
    @SerializedName("color") val color: String,
    @SerializedName("composite") val composite: Boolean,
    @SerializedName("description") val description: Any,
    @SerializedName("last-update") val lastUpdate: String,
    @SerializedName("magnitude") val magnitude: String,
    @SerializedName("title") val title: String,
    @SerializedName("type") val type: Any,
    @SerializedName("values") val values: List<Value>
)