package com.softyorch.dailyelectriccost.ui.activities

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softyorch.dailyelectriccost.domain.redUseCases.RedUsesCases
import com.softyorch.dailyelectriccost.domain.redUseCases.model.mapper.mapToRedDataTruncateModelDomain
import com.softyorch.dailyelectriccost.domain.redUseCases.model.mapper.mapToRedDefaultModelDomain
import com.softyorch.dailyelectriccost.ui.model.RedDataTruncateModelUi
import com.softyorch.dailyelectriccost.ui.model.RedDefaultModelUi
import com.softyorch.dailyelectriccost.utils.Constants.RED21
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val redUsesCases: RedUsesCases) : ViewModel() {

    fun getDataDefault() {
        viewModelScope.launch {
            val response = redUsesCases.getDataDefault(
                RedDefaultModelUi(
                    widget = "balance-electrico",
                    startDate = "2019-01-01T00:00",
                    endDate = "2019-01-31T23:59",
                    timeTruncate = "day"
                ).mapToRedDefaultModelDomain()
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
            val response = redUsesCases.getDataGeoTruncate(
               RedDataTruncateModelUi(
                   widget = "balance-electrico",
                   startDate = "2019-01-01T00:00",
                   endDate = "2019-01-31T23:59",
                   timeTruncate = "day",
                   geo_limit = "peninsular",
                   geo_ids = "8741"
               ).mapToRedDataTruncateModelDomain()
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