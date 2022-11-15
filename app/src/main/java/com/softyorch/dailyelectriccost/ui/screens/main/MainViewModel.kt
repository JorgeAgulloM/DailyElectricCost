package com.softyorch.dailyelectriccost.ui.screens.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softyorch.dailyelectriccost.domain.dsUseCases.DsUseCases
import com.softyorch.dailyelectriccost.domain.dsUseCases.model.mapToSettingsDomain
import com.softyorch.dailyelectriccost.domain.redUseCases.RedUsesCases
import com.softyorch.dailyelectriccost.domain.redUseCases.model.mapper.mapToRedMarketsTruncateModelDomain
import com.softyorch.dailyelectriccost.ui.model.RedMarketsTruncateModelUi
import com.softyorch.dailyelectriccost.ui.model.datastore.SettingsUi
import com.softyorch.dailyelectriccost.ui.model.datastore.mapToSettingsUi
import com.softyorch.dailyelectriccost.ui.model.markets.MarketsModelUi
import com.softyorch.dailyelectriccost.ui.model.markets.mapToMarketsModelUi
import com.softyorch.dailyelectriccost.utils.Constants
import com.softyorch.dailyelectriccost.utils.Constants.RED21
import com.softyorch.dailyelectriccost.utils.funcExtensions.toDateFormattedISO8601
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val redUsesCases: RedUsesCases,
    private val dsUseCases: DsUseCases
) : ViewModel() {

    private var _marketsData = MutableLiveData(MarketsModelUi.emptyMarketsDao)
    val marketsData: LiveData<MarketsModelUi> = _marketsData

    private val _settings = MutableLiveData<SettingsUi>()
    val settings: LiveData<SettingsUi> = _settings

    private val _zone = MutableLiveData<String>("peninsular")
    //val zone: LiveData<String> = _zone

    /** Query data **********************************************/

    private val _category = Constants.LIST_CATEGORY
    private val _startDate = MutableLiveData("2022-11-09T00:00")
    val startDate: LiveData<String> = _startDate
    private val _endDate = MutableLiveData("2022-11-09T23:00")
    private val _timeTruncate = MutableLiveData("hour")

    private val _geoIds = MutableLiveData("8741")

    //private val oneHourInMillis = 3600000L

    init {
        startDate()
        getSettings()
        getDataGeoTruncate()
    }

    fun saveSettings(settingsUi: SettingsUi) {
        viewModelScope.launch(Dispatchers.IO) {
            saveData(settingsUi)
        }
    }

    private fun getSettings() {
        viewModelScope.launch(Dispatchers.IO) {
            getData().collect {
                Log.d(RED21, "VM.getSettings")
                _settings.postValue(it)
            }
        }
    }

    /*fun changeDate(date: String) {
        setDate(date.datePickerToISO8601())
        getDataGeoTruncate()
    }*/

    private fun startDate() {
        setDate(Date().toDateFormattedISO8601())
    }

    private fun setDate(dateFormattedISO8601: String) {
        _startDate.value = dateFormattedISO8601.split("T")[0] + "T00:00"
        _endDate.value = dateFormattedISO8601.split("T")[0] + "T23:00"
        Log.d(RED21, "Values start -> ${_startDate.value} y end ->${_endDate.value}")
    }

    //Fri Nov 11 16:52:24 GMT 2022
    //YYYY-MM-DDTHH:MM => 2022-11-09T00:00
    private fun getDataGeoTruncate() {
        viewModelScope.launch {
            getDataMarkets(
                RedMarketsTruncateModelUi(
                    category = _category[5][0],
                    widget = _category[5][13],
                    startDate = _startDate.value!!,
                    endDate = _endDate.value!!,
                    timeTruncate = _timeTruncate.value!!,
                    geo_limit = _zone.value!!,
                    geo_ids = _geoIds.value!!
                )
            ).let { response ->
                _marketsData.postValue(response)
            }
        }
    }

    /** Data access */

    private suspend fun getDataMarkets(
        redMarketsTruncateModelUi: RedMarketsTruncateModelUi
    ): MarketsModelUi = redUsesCases.getDataMarketsTruncate
        .invoke(redMarketsTruncateModelUi.mapToRedMarketsTruncateModelDomain())
        .mapToMarketsModelUi()

    private suspend fun saveData(settingsUi: SettingsUi) {
        dsUseCases.saveData.invoke(settingsUi.mapToSettingsDomain())
    }

    private fun getData() = dsUseCases.getData.invoke().map { it.mapToSettingsUi() }

    private suspend fun deleteData() {
        dsUseCases.deleteData.invoke()
    }

}