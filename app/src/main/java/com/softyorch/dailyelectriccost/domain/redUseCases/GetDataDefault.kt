package com.softyorch.dailyelectriccost.domain.redUseCases

import com.softyorch.dailyelectriccost.data.repository.red21Repository.Red21Repository
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.mapper.mapToRedDefaultModel
import com.softyorch.dailyelectriccost.domain.redUseCases.model.RedDefaultModelDomain
import javax.inject.Inject

class GetDataDefault @Inject constructor(private val repository: Red21Repository) {
    suspend operator fun invoke(redDefaultModelDomain: RedDefaultModelDomain) =
        repository.getDataDefault(redDefaultModelDomain.mapToRedDefaultModel())
}