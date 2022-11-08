package com.softyorch.dailyelectriccost.domain.redUseCases

import com.softyorch.dailyelectriccost.data.repository.red21Repository.Red21Repository
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.mapper.mapToRedMarketsTruncateModel
import com.softyorch.dailyelectriccost.domain.redUseCases.model.RedDataTruncateModelDomain
import javax.inject.Inject

class GetDataGeoTruncate @Inject constructor(private val repository: Red21Repository) {
    suspend operator fun invoke(redDataTruncateModelDomain: RedDataTruncateModelDomain) =
        repository.getDataGeoTruncate(redDataTruncateModelDomain.mapToRedMarketsTruncateModel())
}