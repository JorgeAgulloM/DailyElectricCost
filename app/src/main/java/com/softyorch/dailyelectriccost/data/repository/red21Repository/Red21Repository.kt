package com.softyorch.dailyelectriccost.data.repository.red21Repository

import com.softyorch.dailyelectriccost.data.network.red21Api.RedService
import com.softyorch.dailyelectriccost.data.network.red21Api.response.Red21
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedDataTruncateModel
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedDefaultModel
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.mapper.mapToRedDataTruncateEntity
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.mapper.mapToRedDefaultEntity
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Red21Repository @Inject constructor( private val api: RedService){
    suspend fun getDataDefault(
        redDefaultModel: RedDefaultModel
    ): Response<Red21>? = api.getDataDefault(
        redDefaultModel.mapToRedDefaultEntity()
    )

    suspend fun getDataGeoTruncate(
       redDataTruncateModel: RedDataTruncateModel
    ): Response<Red21>? = api.getDataGeoTruncate(
        redDataTruncateModel.mapToRedDataTruncateEntity()
    )
}