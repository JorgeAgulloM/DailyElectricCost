package com.softyorch.dailyelectriccost.data.network.red21Api.responseMercados

import com.google.gson.annotations.SerializedName

data class Included(
    @SerializedName("attributes") val attributes: AttributesX,
    @SerializedName("groupId") val groupId: Any,
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String
)