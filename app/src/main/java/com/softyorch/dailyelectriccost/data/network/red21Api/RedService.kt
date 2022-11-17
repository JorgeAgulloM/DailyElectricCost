package com.softyorch.dailyelectriccost.data.network.red21Api

import android.util.Log
import com.softyorch.dailyelectriccost.data.network.red21Api.entity.RedMarketsTruncateEntity
import com.softyorch.dailyelectriccost.data.network.red21Api.entity.RedBalanceEntity
import com.softyorch.dailyelectriccost.data.network.red21Api.entity.RedGenerationTruncateEntity
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
        redBalanceEntity: RedBalanceEntity
    ): Response<Red21Balance>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = redClient.getDataDefault(
                    category = redBalanceEntity.category,
                    widget = redBalanceEntity.widget,
                    startDate = redBalanceEntity.startDate,
                    endDate = redBalanceEntity.endDate,
                    timeTruncate = redBalanceEntity.timeTruncate
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
        redGenerationTruncateEntity: RedGenerationTruncateEntity
    ): Response<Red21Generation>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = redClient.getGenerationGeoTruncate(
                    category = redGenerationTruncateEntity.category,
                    widget = redGenerationTruncateEntity.widget,
                    startDate = redGenerationTruncateEntity.startDate,
                    endDate = redGenerationTruncateEntity.endDate,
                    timeTruncate = redGenerationTruncateEntity.timeTruncate,
                    geo_limit = redGenerationTruncateEntity.geo_limit,
                    geo_ids = redGenerationTruncateEntity.geo_ids
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
        redMarketsTruncateEntity: RedMarketsTruncateEntity
    ): Response<Red21Market>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = redClient.getMarketsGeoTruncate(
                    category = redMarketsTruncateEntity.category,
                    widget = redMarketsTruncateEntity.widget,
                    startDate = redMarketsTruncateEntity.startDate,
                    endDate = redMarketsTruncateEntity.endDate,
                    timeTruncate = redMarketsTruncateEntity.timeTruncate,
                    geo_limit = redMarketsTruncateEntity.geo_limit,
                    geo_ids = redMarketsTruncateEntity.geo_ids
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