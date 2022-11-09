package com.softyorch.dailyelectriccost.data.repository.red21Repository

import android.util.Log
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
import com.softyorch.dailyelectriccost.data.repository.red21Repository.dao.Values
import com.softyorch.dailyelectriccost.utils.Constants.RED21
import com.softyorch.dailyelectriccost.utils.funcExtensions.getHourOfNowToInt
import retrofit2.Response
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Red21Repository @Inject constructor(private val api: RedService) {
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
        val dao = MarketsDao.emptyMarketsDao
        val responseApi =
            api.getMarketsGeoTruncate(redMarketsTruncateModel.mapToRedMarketsTruncateEntity())

        /** recuperar datos del api y almacenarlos en dao */

        responseApi.let { response ->
            if (response != null) {
                if (response.isSuccessful) {

                    response.body()!!.let { body ->
                        body.data.attributes.let { data ->
                            dao.title = data.title
                            dao.lastUpdate = data.lastUpdate
                        }
                        body.included[0].let { included ->
                            dao.type = included.type
                            var lowPrice = 99999999.0
                            var hiPrice = 0.0
                            var currentPrices = 0.0
                            val now = Calendar.HOUR
                            included.attributes.values.forEach { value ->
                                if (value.value > hiPrice) hiPrice = value.value
                                if (value.value < lowPrice) lowPrice = value.value
                                if (value.datetime.getHourOfNowToInt() < now )
                                    currentPrices = value.value
                                dao.values.add(Values(value.value, value.datetime))
                            }
                            dao.lowPrice = lowPrice
                            dao.hiPrice = hiPrice
                            dao.currentPrice = currentPrices
                        }
                    }
                } else {
                    Log.d(RED21, "getDataMarkets.error ->${response.errorBody()}")
                }
            } else {
                Log.d(RED21, "getDataMarkets response is null")
            }
        }

        Log.d(RED21, "getDataMarkets.dao -> $dao")
        return dao
    }
}