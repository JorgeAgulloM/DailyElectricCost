/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.domain.redUseCases

import com.softyorch.dailyelectriccost.data.repository.red21Repository.Red21Repository
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.mapper.mapToRedGenerationTruncateModel
import com.softyorch.dailyelectriccost.domain.redUseCases.model.RedGenerationTruncateDomain
import javax.inject.Inject

class GetDataGenerationTruncate @Inject constructor(private val repository: Red21Repository) {
    suspend operator fun invoke(redGenerationTruncateDomain: RedGenerationTruncateDomain) =
        repository.getGenerateGeoTruncate(redGenerationTruncateDomain.mapToRedGenerationTruncateModel())
}