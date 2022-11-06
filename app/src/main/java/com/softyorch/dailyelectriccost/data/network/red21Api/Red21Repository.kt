package com.softyorch.dailyelectriccost.data.network.red21Api

import com.softyorch.dailyelectriccost.data.network.red21Api.response.Red21
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Red21Repository @Inject constructor( private val api: RedService){
    suspend fun getDataDefault(
        widget: String,
        startDate: String,
        endDate: String,
        timeTrunc: String
    ): Response<Red21>? = api.getDataDefault(
        widget = widget,
        startDate = startDate,
        endDate = endDate,
        timeTrunc = timeTrunc
    )

    suspend fun getDataGeoTruncate(
        widget: String,
        startDate: String,
        endDate: String,
        timeTrunc: String,
        geo_limit: String,
        geo_ids: String
    ): Response<Red21>? = api.getDataGeoTruncate(
        widget = widget,
        startDate = startDate,
        endDate = endDate,
        timeTrunc = timeTrunc,
        geo_limit = geo_limit,
        geo_ids = geo_ids
    )
}