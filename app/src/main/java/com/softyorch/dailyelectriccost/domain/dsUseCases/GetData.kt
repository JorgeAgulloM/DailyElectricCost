/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.domain.dsUseCases

import com.softyorch.dailyelectriccost.data.repository.datastore.DsRepository
import com.softyorch.dailyelectriccost.domain.dsUseCases.model.mapToSettingsDomain
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.map

class GetData(private val dsRepository: DsRepository) {
    operator fun invoke() = dsRepository.getData().map { flow ->
        flow.conflate().map {
            it.mapToSettingsDomain()
        }
    }
}