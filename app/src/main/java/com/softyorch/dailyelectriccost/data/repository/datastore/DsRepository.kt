/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.data.repository.datastore

import android.util.Log
import com.softyorch.dailyelectriccost.data.local.Datastore
import com.softyorch.dailyelectriccost.data.local.entity.mapToSettings
import com.softyorch.dailyelectriccost.data.repository.datastore.model.SettingsModel
import com.softyorch.dailyelectriccost.data.repository.datastore.model.mapToSettingsModel
import com.softyorch.dailyelectriccost.utils.Constants.RED21
import kotlinx.coroutines.flow.*

class DsRepository(private val datastore: Datastore) {

    suspend fun saveData(settingsModel: SettingsModel) {
        datastore.saveData(settingsModel.mapToSettings())
    }

    fun getData() = datastore.getData().map { flow ->
        flow.conflate().catch {
            Log.d(RED21, "Error al recuperar datastore")
            SettingsModel(
                autoLightDark = false,
                manualLightDark = false,
                autoColors = false
            )
        }.map {
            it.mapToSettingsModel()
        }
    }

    suspend fun deleteData() {
        datastore.deleteData()
    }

}