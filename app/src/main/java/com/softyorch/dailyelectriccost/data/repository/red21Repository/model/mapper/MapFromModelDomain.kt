package com.softyorch.dailyelectriccost.data.repository.red21Repository.model.mapper

import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedGenerationTruncateModel
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedBalanceModel
import com.softyorch.dailyelectriccost.domain.redUseCases.model.RedDataTruncateModelDomain
import com.softyorch.dailyelectriccost.domain.redUseCases.model.RedDefaultModelDomain

fun RedDefaultModelDomain.mapToRedDefaultModel(): RedBalanceModel = RedBalanceModel(
    category, widget, startDate, endDate, timeTruncate
)

fun RedDataTruncateModelDomain.mapToRedDataTruncateModel(): RedGenerationTruncateModel = RedGenerationTruncateModel(
    category, widget, startDate, endDate, timeTruncate, geo_limit, geo_ids
)