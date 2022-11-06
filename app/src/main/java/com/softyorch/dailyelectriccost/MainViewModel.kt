package com.softyorch.dailyelectriccost

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softyorch.dailyelectriccost.data.network.red21Api.Red21Repository
import com.softyorch.dailyelectriccost.utils.Constants.RED21
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Red21Repository) : ViewModel() {

    fun getDataDefault() {
        viewModelScope.launch {
            val response = repository.getDataDefault(
                widget = "balance-electrico",
                startDate = "2019-01-01T00:00",
                endDate = "2019-01-31T23:59",
                timeTrunc = "day"
            )
            response.let {
                if (it != null) {
                    if (it.isSuccessful) {
                        Log.d(RED21, "dataVM.default ->${it.body()}")
                    } else {
                        Log.d(RED21, "dataVM.default.error ->${it.errorBody()}")
                    }
                }
            }
        }
    }

    fun getDataGeoTruncate() {
        viewModelScope.launch {
            val response = repository.getDataGeoTruncate(
                widget = "balance-electrico",
                startDate = "2019-01-01T00:00",
                endDate = "2019-01-31T23:59",
                timeTrunc = "day",
                geo_limit = "peninsular",
                geo_ids = "8741"
            )
            response.let {
                if (it != null) {
                    if (it.isSuccessful) {
                        Log.d(RED21, "dataVM.geo ->${it.body()}")
                    } else {
                        Log.d(RED21, "dataVM.geo.error ->${it.errorBody()}")
                    }
                }
            }
        }
    }
}