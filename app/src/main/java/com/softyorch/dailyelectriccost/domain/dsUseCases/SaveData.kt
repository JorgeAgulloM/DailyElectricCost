/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.domain.dsUseCases

import com.softyorch.dailyelectriccost.data.repository.datastore.DsRepository
import com.softyorch.dailyelectriccost.data.repository.datastore.model.mapToSettingsModel
import com.softyorch.dailyelectriccost.domain.dsUseCases.model.SettingsDomain

class SaveData(private val dsRepository: DsRepository) {
    suspend operator fun invoke(settingsDomain: SettingsDomain) {
        dsRepository.saveData(settingsDomain.mapToSettingsModel())
    }
}