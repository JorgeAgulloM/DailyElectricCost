package com.softyorch.dailyelectriccost.data.network.red21Api.responseMercados

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("attributes") val attributes: Attributes,
    @SerializedName("id") val id: String,
    @SerializedName("meta") val meta: Meta,
    @SerializedName("type") val type: String
)