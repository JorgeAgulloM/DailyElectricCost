package com.softyorch.dailyelectriccost.data.network.red21Api.response

import com.google.gson.annotations.SerializedName

data class Included(
    @SerializedName("attributes") val attributes: AttributesX,
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String
)