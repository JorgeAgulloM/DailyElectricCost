package com.softyorch.dailyelectriccost.domain.redUseCases.model.mapper

import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedGenerationTruncateModel
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedDefaultModel

fun RedDefaultModel.mapToRedDefaultModel(): RedDefaultModel = RedDefaultModel(
    category, widget, startDate, endDate, timeTruncate
)

fun RedGenerationTruncateModel.mapToRedDataTruncateModel(): RedGenerationTruncateModel = RedGenerationTruncateModel(
    category, widget, startDate, endDate, timeTruncate, geo_limit, geo_ids
)