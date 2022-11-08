package com.softyorch.dailyelectriccost.data.network.red21Api

import android.util.Log
import com.softyorch.dailyelectriccost.data.network.red21Api.entity.RedDataTruncateEntity
import com.softyorch.dailyelectriccost.data.network.red21Api.entity.RedDefaultEntity
import com.softyorch.dailyelectriccost.data.network.red21Api.response.market.Red21Balance
import com.softyorch.dailyelectriccost.utils.Constants.RED21
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RedService @Inject constructor(private val redClient: RedClient) {
    /** Asimilación de datos del balance eléctrico durante las últimas 24 horas. */
    suspend fun getDataDefault(
        redDefaultEntity: RedDefaultEntity
    ): Response<Red21Balance>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = redClient.getDataDefault(
                    category = redDefaultEntity.category,
                    widget = redDefaultEntity.widget,
                    startDate = redDefaultEntity.startDate,
                    endDate = redDefaultEntity.endDate,
                    timeTruncate = redDefaultEntity.timeTruncate
                )
                Log.d(RED21, "ResponseDefault.Service ->$response")
                response
            } catch (ex: java.lang.Exception) {
                Log.d(RED21, "ResponseDefault.Service.error ->${ex.message.toString()}")
                null
            }
        }
    }

    suspend fun getDataGeoTruncate(
        redDataTruncateEntity: RedDataTruncateEntity
    ): Response<Red21Balance>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = redClient.getDataGeoTruncate(
                    category = redDataTruncateEntity.category,
                    widget = redDataTruncateEntity.widget,
                    startDate = redDataTruncateEntity.startDate,
                    endDate = redDataTruncateEntity.endDate,
                    timeTruncate = redDataTruncateEntity.timeTruncate,
                    geo_limit = redDataTruncateEntity.geo_limit,
                    geo_ids = redDataTruncateEntity.geo_ids
                )
                Log.d(RED21, "ResponseDataTruncate.Service ->$response")
                response
            } catch (ex: java.lang.Exception) {
                Log.d(RED21, "ResponseDataTruncate.Service.error ->${ex.message.toString()}")
                null
            }
        }
    }

}