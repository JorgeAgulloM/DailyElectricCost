package com.softyorch.dailyelectriccost.ui.screens.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softyorch.dailyelectriccost.data.network.red21Api.response.market.Included
import com.softyorch.dailyelectriccost.data.network.red21Api.response.market.Red21Balance
import com.softyorch.dailyelectriccost.domain.redUseCases.RedUsesCases
import com.softyorch.dailyelectriccost.domain.redUseCases.model.mapper.mapToRedDataTruncateModelDomain
import com.softyorch.dailyelectriccost.ui.model.RedDataTruncateModelUi
import com.softyorch.dailyelectriccost.utils.Constants
import com.softyorch.dailyelectriccost.utils.Constants.EMPTY_STRING
import com.softyorch.dailyelectriccost.utils.funcExtensions.toDateFormatted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val redUsesCases: RedUsesCases) : ViewModel() {

    private val _defaultData = MutableLiveData<Red21Balance?>(Red21Balance.red21BalanceEmpty)
    val defaultData: LiveData<Red21Balance?> = _defaultData

    private val _includes =
        MutableLiveData<List<Included?>>(Red21Balance.red21BalanceEmpty.included)
    val includes: LiveData<List<Included?>> = _includes


    private val _marketType = MutableLiveData<String>(EMPTY_STRING)
    val marketType: LiveData<String> = _marketType

    private val _lastUpdate = MutableLiveData<String>(EMPTY_STRING)
    val lastUpdate: LiveData<String> = _lastUpdate

    private val _price = MutableLiveData<Double>(0.0)
    val price: LiveData<Double> = _price

    private val _dateTime = MutableLiveData<String>(EMPTY_STRING)
    val dateTime: LiveData<String> = _dateTime

    /** Query data **********************************************/

    private val _category = Constants.LIST_CATEGORY
    private val _startDate = MutableLiveData("2022-11-07T00:00")
    private val _endDate = MutableLiveData("2022-11-13T23:00")
    private val _timeTruncate = MutableLiveData("hour")
    private val _geoLimit = MutableLiveData("peninsular")
    val geoLimit: LiveData<String> = _geoLimit

    private val _geoIds = MutableLiveData("8741")

    private val oneHourInMillis = 3600000L
    init {
        //getDataDefault()
        getDataGeoTruncate()
    }

    /*fun getDataDefault() {
        viewModelScope.launch {
            val response = redUsesCases.getDataDefault(
                RedDefaultModelUi(
                    category = Constants.LIST_CATEGORY[5][0],
                    widget = Constants.LIST_CATEGORY[5][13],
                    startDate = "2022-11-07T17:00",
                    endDate = "2022-11-07T23:00",
                    timeTruncate = "hour"
                ).mapToRedDefaultModelDomain()
            )
            response.let {
                if (it != null) {
                    if (it.isSuccessful) {
                        _defaultData.postValue(it.body())
                        Log.d(Constants.RED21, "dataVM.default ->${it.body()}")
                    } else {
                        Log.d(Constants.RED21, "dataVM.default.error ->${it.errorBody()}")
                    }
                }
            }
        }
    }*/


    fun getDataGeoTruncate() {

        viewModelScope.launch {
            redUsesCases.getDataGeoTruncate(
                RedDataTruncateModelUi(
                    category = _category[5][0],
                    widget = _category[5][13],
                    startDate = _startDate.value!!,
                    endDate = _endDate.value!!,/*if (sdk26AndUp) {
                        (Date.from(Instant.now())).toString()
                    } else {
                        LocalDateTime.now().toString()
                    },//_endDate.value!!,*/
                    timeTruncate = _timeTruncate.value!!,
                    geo_limit = _geoLimit.value!!,
                    geo_ids = _geoIds.value!!
                ).mapToRedDataTruncateModelDomain()
            ).let { response ->
                if (response != null) {
                    if (response.isSuccessful) {
                        _defaultData.postValue(response.body())
                        _includes.postValue(response.body()!!.included)

                        response.body()?.included?.get(0)?.attributes.let { attr ->
                            _marketType.postValue(attr?.title)
                            _lastUpdate.postValue(attr?.lastUpdate?.toDateFormatted() ?: "Desconocido")
                            attr?.values?.get(1).let { values ->
                                _price.postValue(values?.value ?: 0.0)
                                _dateTime.postValue(values?.datetime?.toDateFormatted() ?: "Desconocido")
                            }
                        }

                        Log.d(Constants.RED21, "dataVM.geo ->${response.body()}")
                    } else {
                        Log.d(Constants.RED21, "dataVM.geo.error ->${response.errorBody()}")
                    }
                }
            }
        }
    }

}