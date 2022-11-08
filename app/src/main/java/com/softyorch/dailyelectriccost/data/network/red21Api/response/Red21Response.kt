package com.softyorch.dailyelectriccost.data.network.red21Api.response

import com.google.gson.annotations.SerializedName
import com.softyorch.dailyelectriccost.data.network.red21Api.response.*
import com.softyorch.dailyelectriccost.utils.Constants.EMPTY_STRING

data class Red21Response(
    @SerializedName("data") val data: Data,
    @SerializedName("included") val included: List<Included>
) {
    companion object {
        val red21ResponseEmpty: Red21Response = Red21Response(
            data = Data(
                attributes = Attributes(
                    description = EMPTY_STRING,
                    lastUpdate = EMPTY_STRING,
                    title = EMPTY_STRING
                ),
                id = EMPTY_STRING,
                meta = Meta(
                    cacheControl = CacheControl(
                        cache = EMPTY_STRING
                    )
                ),
                type = EMPTY_STRING
            ),
            included = listOf(
                Included(
                    attributes = AttributesX(
                        color = EMPTY_STRING,
                        composite = false,
                        description = EMPTY_STRING,
                        lastUpdate = EMPTY_STRING,
                        magnitude = EMPTY_STRING,
                        title = EMPTY_STRING,
                        type = EMPTY_STRING,
                        values = listOf(
                            Value(
                                datetime = EMPTY_STRING,
                                percentage = 0,
                                value = 0.0
                            )
                        )
                    ),
                    groupId = EMPTY_STRING,
                    id = EMPTY_STRING,
                    type = EMPTY_STRING
                )
            )
        )
    }
}