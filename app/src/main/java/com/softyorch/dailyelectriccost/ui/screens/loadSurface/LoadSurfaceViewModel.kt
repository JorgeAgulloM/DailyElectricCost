/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.loadSurface

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softyorch.dailyelectriccost.domain.dsUseCases.DsUseCases
import com.softyorch.dailyelectriccost.ui.model.datastore.SettingsUi
import com.softyorch.dailyelectriccost.ui.model.datastore.mapToSettingsUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class LoadSurfaceViewModel @Inject constructor(private val dsUseCases: DsUseCases) : ViewModel() {

    private val _settings = MutableLiveData<SettingsUi>()
    val settings: LiveData<SettingsUi> = _settings

    init {
        getSettings()
    }

    private fun getSettings() {
        viewModelScope.launch(Dispatchers.IO){
            getData().flowOn(Dispatchers.IO).collect {
                _settings.postValue(it)
            }
        }
    }

    private fun getData() = dsUseCases.getData.invoke().map { it.mapToSettingsUi() }

}