package com.softyorch.dailyelectriccost.data.network.red21Api.response

import com.google.gson.annotations.SerializedName

data class Red21(
    @SerializedName("`data`") val data: Data,
    @SerializedName("included") val included: List<Included>
)