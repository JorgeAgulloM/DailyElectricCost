package com.softyorch.dailyelectriccost.data.network.red21Api.response.balance

import com.google.gson.annotations.SerializedName

data class CacheControl(
    @SerializedName("cache") val cache: String
)