package com.softyorch.dailyelectriccost.data.repository.red21Repository.model.mapper

import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedMarketsTruncateModel
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedBalanceModel
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedGenerationTruncateModel
import com.softyorch.dailyelectriccost.domain.redUseCases.model.RedMarketsTruncateModelDomain
import com.softyorch.dailyelectriccost.domain.redUseCases.model.RedBalanceModelDomain
import com.softyorch.dailyelectriccost.domain.redUseCases.model.RedGenerationTruncateDomain

fun RedBalanceModelDomain.mapToRedBalanceModel(): RedBalanceModel = RedBalanceModel(
    category, widget, startDate, endDate, timeTruncate
)

fun RedGenerationTruncateDomain.mapToRedGenerationTruncateModel(): RedGenerationTruncateModel = RedGenerationTruncateModel(
    category, widget, startDate, endDate, timeTruncate, geo_limit, geo_ids
)

fun RedMarketsTruncateModelDomain.mapToRedMarketsTruncateModel(): RedMarketsTruncateModel = RedMarketsTruncateModel(
    category, widget, startDate, endDate, timeTruncate, geo_limit, geo_ids
)