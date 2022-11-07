package com.softyorch.dailyelectriccost.data.network.red21Api.response

import com.google.gson.annotations.SerializedName

data class CacheControl(
    @SerializedName("cache") val cache: String,
    @SerializedName("expireAt") val expireAt: String
)