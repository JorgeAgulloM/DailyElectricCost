/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.softyorch.dailyelectriccost.data.local.entity.Settings
import kotlinx.coroutines.flow.map

class Datastore(private val context: Context) {

    suspend fun saveData(settings: Settings) {
        context.datastore.edit { ds ->
            ds[booleanPreferencesKey("autoLightDark")] = settings.autoLightDark
            ds[booleanPreferencesKey("manualLightDark")] = settings.manualLightDark
            ds[booleanPreferencesKey("autoColors")] = settings.autoColors
        }
    }

    fun getData() = try {
        Result.success(
            context.datastore.data.map { ds ->
                Settings(
                    autoLightDark = ds[booleanPreferencesKey("autoLightDark")] ?: false,
                    manualLightDark = ds[booleanPreferencesKey("manualLightDark")] ?: false,
                    autoColors = ds[booleanPreferencesKey("autoColors")] ?: false
                )
            }
        )
    } catch (ex: Exception) {
        Result.failure(ex.fillInStackTrace())
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