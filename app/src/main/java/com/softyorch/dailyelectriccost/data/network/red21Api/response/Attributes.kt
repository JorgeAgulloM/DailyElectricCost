package com.softyorch.dailyelectriccost.data.network.red21Api.response

import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("description") val description: String,
    @SerializedName("last-update") val lastUpdate: String,
    @SerializedName("title") val title: String
)