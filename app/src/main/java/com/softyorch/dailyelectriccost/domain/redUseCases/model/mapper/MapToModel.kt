package com.softyorch.dailyelectriccost.domain.redUseCases.model.mapper

import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedDataTruncateModel
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedDefaultModel

fun RedDefaultModel.mapToRedDefaultModel(): RedDefaultModel = RedDefaultModel(
    category, widget, startDate, endDate, timeTruncate
)

fun RedDataTruncateModel.mapToRedDataTruncateModel(): RedDataTruncateModel = RedDataTruncateModel(
    category, widget, startDate, endDate, timeTruncate, geo_limit, geo_ids
)