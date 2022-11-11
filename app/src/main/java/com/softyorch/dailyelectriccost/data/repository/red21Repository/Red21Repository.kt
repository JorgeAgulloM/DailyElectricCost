package com.softyorch.dailyelectriccost.data.repository.red21Repository

import com.softyorch.dailyelectriccost.data.network.red21Api.RedService
import com.softyorch.dailyelectriccost.data.network.red21Api.response.balance.Red21Balance
import com.softyorch.dailyelectriccost.data.network.red21Api.response.generation.Red21Generation
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedMarketsTruncateModel
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedBalanceModel
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedGenerationTruncateModel
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.mapper.mapToGenerationTruncateEntity
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.mapper.mapToRedMarketsTruncateEntity
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.mapper.mapToRedBalanceEntity
import com.softyorch.dailyelectriccost.data.repository.red21Repository.dao.MarketsDao
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Red21Repository @Inject constructor(private val api: RedService) : IRed21Repository {
    suspend fun getDataDefault(
        redBalanceModel: RedBalanceModel
    ): Response<Red21Balance>? = api.getDataDefault(
        redBalanceModel.mapToRedBalanceEntity()
    )

    suspend fun getGenerateGeoTruncate(
        redGenerationTruncateModel: RedGenerationTruncateModel
    ): Response<Red21Generation>? = api.getGenerationDefault(
        redGenerationTruncateModel.mapToGenerationTruncateEntity()
    )

    suspend fun getMarketsGeoTruncate(redMarketsTruncateModel: RedMarketsTruncateModel): MarketsDao {
        val responseApi =
            api.getMarketsGeoTruncate(redMarketsTruncateModel.mapToRedMarketsTruncateEntity())
        return loadDao(responseApi)
    }
}