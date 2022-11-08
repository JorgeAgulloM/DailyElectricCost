package com.softyorch.dailyelectriccost.data.network.red21Api.response.balance

import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("description") val description: Any,
    @SerializedName("last-update") val lastUpdate: String,
    @SerializedName("title") val title: String
)