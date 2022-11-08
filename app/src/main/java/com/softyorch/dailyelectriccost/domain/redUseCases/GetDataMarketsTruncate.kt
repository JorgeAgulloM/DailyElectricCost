package com.softyorch.dailyelectriccost.domain.redUseCases

import com.softyorch.dailyelectriccost.data.repository.red21Repository.Red21Repository
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.mapper.mapToRedMarketsTruncateModel
import com.softyorch.dailyelectriccost.domain.redUseCases.model.RedMarketsTruncateModelDomain
import javax.inject.Inject

class GetDataMarketsTruncate @Inject constructor(private val repository: Red21Repository) {
    suspend operator fun invoke(redMarketsTruncateModelDomain: RedMarketsTruncateModelDomain) =
        repository.getDataGeoTruncate(redMarketsTruncateModelDomain.mapToRedMarketsTruncateModel())
}