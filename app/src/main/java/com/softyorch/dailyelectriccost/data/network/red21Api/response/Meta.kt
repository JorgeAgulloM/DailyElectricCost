package com.softyorch.dailyelectriccost.data.network.red21Api.response

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("cache-control") val cacheControl: CacheControl
)