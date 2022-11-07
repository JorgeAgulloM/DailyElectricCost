package com.softyorch.dailyelectriccost.data.network.red21Api.response

import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("attributes") val attributes: AttributesXX,
    @SerializedName("groupId") val groupId: String,
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String
)