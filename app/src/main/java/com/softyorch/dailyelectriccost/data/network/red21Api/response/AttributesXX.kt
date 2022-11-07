package com.softyorch.dailyelectriccost.data.network.red21Api.response

import com.google.gson.annotations.SerializedName

data class AttributesXX(
    @SerializedName("color") val color: String,
    @SerializedName("composite") val composite: Boolean,
    @SerializedName("description") val description: String,
    @SerializedName("last-update") val lastUpdate: String,
    @SerializedName("magnitude") val magnitude: Any,
    @SerializedName("title") val title: String,
    @SerializedName("total") val total: Double,
    @SerializedName("type") val type: String,
    @SerializedName("values") val values: List<Value>
)