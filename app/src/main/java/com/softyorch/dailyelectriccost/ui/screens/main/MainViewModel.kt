package com.softyorch.dailyelectriccost.ui.screens.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softyorch.dailyelectriccost.data.network.red21Api.responseMercados.Red21Mercados
import com.softyorch.dailyelectriccost.domain.redUseCases.RedUsesCases
import com.softyorch.dailyelectriccost.domain.redUseCases.model.mapper.mapToRedDataTruncateModelDomain
import com.softyorch.dailyelectriccost.domain.redUseCases.model.mapper.mapToRedDefaultModelDomain
import com.softyorch.dailyelectriccost.ui.model.RedDataTruncateModelUi
import com.softyorch.dailyelectriccost.ui.model.RedDefaultModelUi
import com.softyorch.dailyelectriccost.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val redUsesCases: RedUsesCases) : ViewModel() {

    private val _defaultData = MutableLiveData<Red21Mercados?>(Red21Mercados.red21MercadosEmpty)
    val defaultData: LiveData<Red21Mercados?> = _defaultData

    init {
        //getDataDefault()
        getDataGeoTruncate()
    }

    fun getDataDefault() {
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
    }

    fun getDataGeoTruncate() {
        viewModelScope.launch {
            val response = redUsesCases.getDataGeoTruncate(
                RedDataTruncateModelUi(
                    category = Constants.LIST_CATEGORY[5][0],
                    widget = Constants.LIST_CATEGORY[5][13],
                    startDate = "2022-11-07T17:00",
                    endDate = "2022-11-07T18:00",
                    timeTruncate = "hour",
                    geo_limit = "peninsular",
                    geo_ids = "8741"
                ).mapToRedDataTruncateModelDomain()
            )
            response.let {
                if (it != null) {
                    if (it.isSuccessful) {
                        _defaultData.postValue(it.body())
                        Log.d(Constants.RED21, "dataVM.geo ->${it.body()}")
                    } else {
                        Log.d(Constants.RED21, "dataVM.geo.error ->${it.errorBody()}")
                    }
                }
            }
        }
    }

}