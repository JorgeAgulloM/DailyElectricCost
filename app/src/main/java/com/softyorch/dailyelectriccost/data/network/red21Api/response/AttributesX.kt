package com.softyorch.dailyelectriccost.data.network.red21Api.response

import com.google.gson.annotations.SerializedName

data class AttributesX(
    @SerializedName("content") val content: List<Content>,
    @SerializedName("description") val description: String,
    @SerializedName("last-update") val lastUpdate: String,
    @SerializedName("magnitude") val magnitude: Any,
    @SerializedName("title") val title: String
)