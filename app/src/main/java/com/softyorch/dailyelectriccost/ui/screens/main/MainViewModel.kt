package com.softyorch.dailyelectriccost.ui.screens.main

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softyorch.dailyelectriccost.domain.redUseCases.RedUsesCases
import com.softyorch.dailyelectriccost.domain.redUseCases.model.mapper.mapToRedMarketsTruncateModelDomain
import com.softyorch.dailyelectriccost.ui.model.RedMarketsTruncateModelUi
import com.softyorch.dailyelectriccost.ui.model.markets.MarketsModelUi
import com.softyorch.dailyelectriccost.ui.model.markets.mapToMarketsModelUi
import com.softyorch.dailyelectriccost.utils.Constants
import com.softyorch.dailyelectriccost.utils.Constants.RED21
import com.softyorch.dailyelectriccost.utils.funcExtensions.toDateFormattedISO8601
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val redUsesCases: RedUsesCases) : ViewModel() {

    private val _marketsData = MutableLiveData(MarketsModelUi.emptyMarketsDao)
    val marketsData: LiveData<MarketsModelUi> = _marketsData

    private val _zone = MutableLiveData<String>("peninsular")
    val zone: LiveData<String> = _zone

    /** Query data **********************************************/

    private val _category = Constants.LIST_CATEGORY
    private val _startDate = MutableLiveData("2022-11-09T00:00")
    val startDate: LiveData<String> = _startDate
    private val _endDate = MutableLiveData("2022-11-09T23:00")
    private val _timeTruncate = MutableLiveData("hour")

    private val _geoIds = MutableLiveData("8741")

    private val oneHourInMillis = 3600000L

    init {
        startDate()
        getDataGeoTruncate()
    }

    fun loadData(date: String) {
        Log.d(RED21, "date ->$date")
    }

    private fun startDate() {
        val date = Date().toDateFormattedISO8601()
        _startDate.value = date.split("T")[0] + "T00:00"
        _endDate.value = date.split("T")[0] + "T23:00"
    }

    fun loadDataFrom(zoneQuery: ZoneQuery) {
        /** no se puede utilizar ya que la API devuelve
         * los mismos valores utilizando cualquier filtrado en este ambito
         * */
        /*val zone = when (zoneQuery) {
            ZoneQuery.Peninsula -> ZoneQuery.Peninsula
            ZoneQuery.Canarias -> ZoneQuery.Canarias
            ZoneQuery.Baleares -> ZoneQuery.Baleares
            ZoneQuery.Ceuta -> ZoneQuery.Ceuta
            ZoneQuery.Melilla -> ZoneQuery.Melilla
        }
        _zone.value = zone.zone
        _geoIds.value = zone.geoId
        getDataGeoTruncate()*/
    }

    private fun getDate() {
        val date = Date()


    }


    //Fri Nov 11 16:52:24 GMT 2022
    //YYYY-MM-DDTHH:MM => 2022-11-09T00:00
    private fun getDataGeoTruncate() {


        viewModelScope.launch {
            redUsesCases.getDataMarketsTruncate(
                RedMarketsTruncateModelUi(
                    category = _category[5][0],
                    widget = _category[5][13],
                    startDate = _startDate.value!!,
                    endDate = _endDate.value!!,/*if (sdk26AndUp) {
                        (Date.from(Instant.now())).toString()
                    } else {
                        LocalDateTime.now().toString()
                    },//_endDate.value!!,*/
                    timeTruncate = _timeTruncate.value!!,
                    geo_limit = _zone.value!!,
                    geo_ids = _geoIds.value!!
                ).mapToRedMarketsTruncateModelDomain()
            ).let { response ->
                _marketsData.postValue(response.mapToMarketsModelUi())
            }
        }
    }

}