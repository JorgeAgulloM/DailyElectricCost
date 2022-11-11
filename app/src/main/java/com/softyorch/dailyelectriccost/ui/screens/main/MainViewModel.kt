package com.softyorch.dailyelectriccost.ui.screens.main

import android.util.Log
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
    private val _endDate = MutableLiveData("2022-11-09T23:00")
    private val _timeTruncate = MutableLiveData("hour")

    private val _geoIds = MutableLiveData("8741")

    private val oneHourInMillis = 3600000L

    init {
        getDataGeoTruncate()
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

    private fun getDataGeoTruncate() {
        Log.d(
            RED21, "dataSend -> ${
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
                )
            }"
        )

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