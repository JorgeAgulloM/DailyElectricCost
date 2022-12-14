/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.data.local

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.softyorch.dailyelectriccost.data.local.entity.Settings
import com.softyorch.dailyelectriccost.utils.Constants.RED21
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Datastore @Inject constructor(private val context: Context) {

    suspend fun saveData(settings: Settings) {
        context.datastore.edit { ds ->
            ds[booleanPreferencesKey("autoLightDark")] = settings.autoLightDark
            ds[booleanPreferencesKey("manualLightDark")] = settings.manualLightDark
            ds[booleanPreferencesKey("autoColors")] = settings.autoColors
        }
    }

    fun getData() = context.datastore.data.map { ds ->
        Settings(
            autoLightDark = ds[booleanPreferencesKey("autoLightDark")] ?: false,
            manualLightDark = ds[booleanPreferencesKey("manualLightDark")] ?: false,
            autoColors = ds[booleanPreferencesKey("autoColors")] ?: false
        )
    }


    suspend fun deleteData() {
        context.datastore.edit { ds ->
            ds[booleanPreferencesKey("autoLightDark")] = false
            ds[booleanPreferencesKey("manualLightDark")] = false
            ds[booleanPreferencesKey("autoColors")] = false
        }
    }

}

val Context.datastore by preferencesDataStore(name = "DailyElectricCost")