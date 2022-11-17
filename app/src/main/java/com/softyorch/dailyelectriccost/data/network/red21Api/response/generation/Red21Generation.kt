/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.data.network.red21Api.response.generation

import com.google.gson.annotations.SerializedName
import com.softyorch.dailyelectriccost.utils.Constants.EMPTY_STRING

data class Red21Generation(
    @SerializedName("data") val data: Data,
    @SerializedName("included") val included: List<Included>
){
    companion object {
        val red21GenerationEmpty: Red21Generation = Red21Generation(
            data = Data(
                attributes = Attributes(
                    description = EMPTY_STRING,
                    lastUpdate = EMPTY_STRING,
                    title = EMPTY_STRING
                ),
                id = EMPTY_STRING,
                meta = Meta(
                    cacheControl = CacheControl(
                        cache = EMPTY_STRING,
                        expireAt = EMPTY_STRING
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