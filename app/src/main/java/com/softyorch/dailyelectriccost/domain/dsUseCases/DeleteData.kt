/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.domain.dsUseCases

import com.softyorch.dailyelectriccost.data.repository.datastore.DsRepository

class DeleteData(private val dsRepository: DsRepository) {
    suspend operator fun invoke() {
        dsRepository.deleteData()
    }
}