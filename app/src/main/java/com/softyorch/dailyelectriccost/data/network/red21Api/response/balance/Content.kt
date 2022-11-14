/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.data.network.red21Api.response.balance

import com.google.gson.annotations.SerializedName

data class Content(
        @SerializedName("attributes") val attributes: AttributesXX,
        @SerializedName("groupId") val groupId: String,
        @SerializedName("id") val id: String,
        @SerializedName("type") val type: String
)