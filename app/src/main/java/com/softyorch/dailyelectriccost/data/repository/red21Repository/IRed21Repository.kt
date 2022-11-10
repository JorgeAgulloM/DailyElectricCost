/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.data.repository.red21Repository

import android.util.Log
import com.softyorch.dailyelectriccost.data.network.red21Api.response.market.Red21Market
import com.softyorch.dailyelectriccost.data.repository.red21Repository.dao.MarketsDao
import com.softyorch.dailyelectriccost.data.repository.red21Repository.dao.Values
import com.softyorch.dailyelectriccost.utils.Constants
import com.softyorch.dailyelectriccost.utils.funcExtensions.getHourOfCalendarToInt
import com.softyorch.dailyelectriccost.utils.funcExtensions.getHourOfNowToInt
import retrofit2.Response
import java.util.*

interface IRed21Repository{
    fun loadDao(responseApi: Response<Red21Market>?): MarketsDao {
        val dao = MarketsDao.emptyMarketsDao
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
                            var loadAverage = 0.0
                            var divideAverage = 0.0
                            val nowHour =
                                Calendar.getInstance().time.toString().getHourOfCalendarToInt()
                            included.attributes.values.forEach { value ->
                                if (value.value > 0.0) {
                                    loadAverage += value.value
                                    divideAverage++
                                }
                                if (value.value > hiPrice) hiPrice = value.value
                                if (value.value < lowPrice) lowPrice = value.value
                                if (value.datetime.getHourOfNowToInt() == nowHour)
                                    currentPrices = value.value
                                dao.values.add(Values(value.value, value.datetime))
                            }
                            dao.lowPrice = lowPrice
                            dao.hiPrice = hiPrice
                            dao.currentPrice = currentPrices
                            dao.avgPrice = loadAverage / divideAverage
                        }
                    }
                } else {
                    Log.d(Constants.RED21, "getDataMarkets.error ->${response.errorBody()}")
                }
            } else {
                Log.d(Constants.RED21, "getDataMarkets response is null")
            }
        }
        return dao
    }
}