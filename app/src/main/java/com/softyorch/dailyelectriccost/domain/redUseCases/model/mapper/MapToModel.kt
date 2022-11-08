package com.softyorch.dailyelectriccost.domain.redUseCases.model.mapper

import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedMarketsTruncateModel
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedBalanceModel

fun RedBalanceModel.mapToRedDefaultModel(): RedBalanceModel = RedBalanceModel(
    category, widget, startDate, endDate, timeTruncate
)

fun RedMarketsTruncateModel.mapToRedDataTruncateModel(): RedMarketsTruncateModel = RedMarketsTruncateModel(
    category, widget, startDate, endDate, timeTruncate, geo_limit, geo_ids
)