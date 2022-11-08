package com.softyorch.dailyelectriccost.data.network.red21Api

import android.util.Log
import com.softyorch.dailyelectriccost.data.network.red21Api.entity.RedDataTruncateEntity
import com.softyorch.dailyelectriccost.data.network.red21Api.entity.RedDefaultEntity
import com.softyorch.dailyelectriccost.data.network.red21Api.response.balance.Red21Balance
import com.softyorch.dailyelectriccost.data.network.red21Api.response.generation.Red21Generation
import com.softyorch.dailyelectriccost.data.network.red21Api.response.market.Red21Market
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
                Log.d(RED21, "Response.getDataDefault.Service ->$response")
                response
            } catch (ex: java.lang.Exception) {
                Log.d(RED21, "Response.getDataDefault.Service.error ->${ex.message.toString()}")
                null
            }
        }
    }

    /** Asimilación de datos de la generación de energía del último dato guardado. */
    suspend fun getGenerationDefault(
        redDefaultEntity: RedDefaultEntity
    ): Response<Red21Generation>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = redClient.getGenerationGeoTruncate(
                    category = redDefaultEntity.category,
                    widget = redDefaultEntity.widget,
                    startDate = redDefaultEntity.startDate,
                    endDate = redDefaultEntity.endDate,
                    timeTruncate = redDefaultEntity.timeTruncate
                )
                Log.d(RED21, "Response.getGenerationDefault.Service ->$response")
                response
            } catch (ex: java.lang.Exception) {
                Log.d(RED21, "Response.getGenerationDefault.Service.error ->${ex.message.toString()}")
                null
            }
        }
    }

    /** Asimilación de datos de los mercados en tiempo real, actualizado cada hora. */
    suspend fun getMarketsGeoTruncate(
        redDataTruncateEntity: RedDataTruncateEntity
    ): Response<Red21Market>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = redClient.getMarketsGeoTruncate(
                    category = redDataTruncateEntity.category,
                    widget = redDataTruncateEntity.widget,
                    startDate = redDataTruncateEntity.startDate,
                    endDate = redDataTruncateEntity.endDate,
                    timeTruncate = redDataTruncateEntity.timeTruncate,
                    geo_limit = redDataTruncateEntity.geo_limit,
                    geo_ids = redDataTruncateEntity.geo_ids
                )
                Log.d(RED21, "Response.getMarketsGeoTruncate.Service ->$response")
                response
            } catch (ex: java.lang.Exception) {
                Log.d(RED21, "Response.getMarketsGeoTruncate.Service.error ->${ex.message.toString()}")
                null
            }
        }
    }

}