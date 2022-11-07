package com.softyorch.dailyelectriccost.data.repository.red21Repository.model.mapper

import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedDataTruncateModel
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedDefaultModel
import com.softyorch.dailyelectriccost.domain.redUseCases.model.RedDataTruncateModelDomain
import com.softyorch.dailyelectriccost.domain.redUseCases.model.RedDefaultModelDomain

fun RedDefaultModelDomain.mapToRedDefaultModel(): RedDefaultModel = RedDefaultModel(
    category, widget, startDate, endDate, timeTruncate
)

fun RedDataTruncateModelDomain.mapToRedDataTruncateModel(): RedDataTruncateModel = RedDataTruncateModel(
    category, widget, startDate, endDate, timeTruncate, geo_limit, geo_ids
)