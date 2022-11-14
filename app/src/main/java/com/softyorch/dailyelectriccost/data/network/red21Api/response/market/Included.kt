/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.data.network.red21Api.response.market

import com.google.gson.annotations.SerializedName

data class Included(
    @SerializedName("attributes") val attributes: AttributesX,
    @SerializedName("groupId") val groupId: Any,
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String
)