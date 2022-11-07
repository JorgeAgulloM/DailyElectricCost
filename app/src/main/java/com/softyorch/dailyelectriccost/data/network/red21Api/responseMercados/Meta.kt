package com.softyorch.dailyelectriccost.data.network.red21Api.responseMercados

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("cache-control") val cacheControl: CacheControl
)