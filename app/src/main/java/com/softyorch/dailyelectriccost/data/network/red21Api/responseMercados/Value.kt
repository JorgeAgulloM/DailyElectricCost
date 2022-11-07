package com.softyorch.dailyelectriccost.data.network.red21Api.responseMercados

import com.google.gson.annotations.SerializedName

data class Value(
    @SerializedName("datetime") val datetime: String,
    @SerializedName("percentage") val percentage: Int,
    @SerializedName("value") val value: Double
)