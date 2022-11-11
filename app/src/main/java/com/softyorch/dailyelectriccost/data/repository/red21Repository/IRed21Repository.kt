/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.data.repository.red21Repository

import android.util.Log
import com.softyorch.dailyelectriccost.data.network.red21Api.response.market.Red21Market
import com.softyorch.dailyelectriccost.data.network.red21Api.response.market.Value
import com.softyorch.dailyelectriccost.data.repository.red21Repository.dao.MarketsDao
import com.softyorch.dailyelectriccost.data.repository.red21Repository.dao.Values
import com.softyorch.dailyelectriccost.utils.Constants.RED21
import com.softyorch.dailyelectriccost.utils.funcExtensions.getHourOfCalendarToInt
import com.softyorch.dailyelectriccost.utils.funcExtensions.getHourOfDate
import com.softyorch.dailyelectriccost.utils.funcExtensions.getHourOfNowToInt
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

interface IRed21Repository {
    fun loadDao(responseApi: Response<Red21Market>?): MarketsDao {
        val dao = MarketsDao.emptyMarketsDao
        responseApi.let { response ->
            if (response != null) {
                if (response.isSuccessful) {

                    response.body()!!.let { body ->
                        getData(body, dao)
                        getIncluded(body, dao)
                    }
                } else {
                    Log.d(RED21, "getDataMarkets.error ->${response.errorBody()}")
                }
            } else {
                Log.d(RED21, "getDataMarkets response is null")
            }
        }
        return dao
    }

    private fun getData(
        body: Red21Market,
        dao: MarketsDao
    ) {
        body.data.attributes.let { data ->
            dao.title = data.title
            dao.lastUpdate = data.lastUpdate
        }
    }

    private fun getIncluded(
        body: Red21Market,
        dao: MarketsDao
    ) {
        body.included[0].let { included ->
            dao.type = included.type


            var currentPrices = 0.0
            var loadAverage = 0.0
            var divideAverage = 0.0
            val nowHour =
                Calendar.getInstance().time.toString().getHourOfCalendarToInt()

            included.attributes.values.let {
                dao.apply {
                    it.forEach { value ->
                        if (value.value > 0.0) {
                            loadAverage += value.value
                            divideAverage++
                        }
                        if (value.datetime.getHourOfNowToInt() == nowHour) currentPrices =
                            value.value
                        values.add(Values(value.value, value.datetime))
                    }
                    currentPrice = currentPrices
                    avgPrice = loadAverage / divideAverage
                    bestLowRange = bestHoursRange(it)
                    lowPrice = it.minOf { value -> value.value }
                    hiPrice = it.maxOf { value -> value.value }
                    lowHour = it.findLast { value -> value.value == dao.lowPrice }!!.datetime.getHourOfDate()
                    hiHour = it.findLast { value -> value.value == dao.hiPrice }!!.datetime.getHourOfDate()
                }
            }
        }
    }

    private fun MarketsDao.bestHoursRange(it: List<Value>): String {
        val listOfHoursLowPrice = it.filter { value -> value.value <= avgPrice }

        var lastHourChecked = 0
        val blockListHours = mutableListOf<Int>()
        val listOfBlockListHours = mutableListOf<MutableList<Int>>()
        listOfHoursLowPrice.forEach { value ->
            lastHourChecked = if (lastHourChecked == 0) value.datetime.getHourOfNowToInt()
            else {
                val now = value.datetime.getHourOfNowToInt()
                blockListHours.add(lastHourChecked)
                if (lastHourChecked + 1 != now) {
                    listOfBlockListHours.add(ArrayList(blockListHours))
                    blockListHours.clear()
                }
                now
            }
        }
        blockListHours.add(lastHourChecked)
        listOfBlockListHours.add(ArrayList(blockListHours))

        //
        blockListHours.clear()
        listOfBlockListHours.sortByDescending { newList -> newList.size }
        blockListHours.addAll(listOfBlockListHours[0])

        return "El rango de horas más económico es de la ${
            blockListHours.first()
        } am hasta las ${
            blockListHours.last()
        } pm"

    }

}