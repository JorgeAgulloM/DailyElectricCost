package com.softyorch.dailyelectriccost.data.repository.red21Repository.model.mapper

import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedMarketsTruncateModel
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedBalanceModel
import com.softyorch.dailyelectriccost.domain.redUseCases.model.RedDataTruncateModelDomain
import com.softyorch.dailyelectriccost.domain.redUseCases.model.RedDefaultModelDomain

fun RedDefaultModelDomain.mapToRedBalanceModel(): RedBalanceModel = RedBalanceModel(
    category, widget, startDate, endDate, timeTruncate
)

fun RedDataTruncateModelDomain.mapToRedMarketsTruncateModel(): RedMarketsTruncateModel = RedMarketsTruncateModel(
    category, widget, startDate, endDate, timeTruncate, geo_limit, geo_ids
)