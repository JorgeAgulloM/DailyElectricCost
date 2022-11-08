package com.softyorch.dailyelectriccost.domain.redUseCases.model.mapper

import com.softyorch.dailyelectriccost.domain.redUseCases.model.RedMarketsTruncateModelDomain
import com.softyorch.dailyelectriccost.domain.redUseCases.model.RedBalanceModelDomain
import com.softyorch.dailyelectriccost.ui.model.RedMarketsTruncateModelUi
import com.softyorch.dailyelectriccost.ui.model.RedBalanceModelUi

fun RedBalanceModelUi.mapToRedDefaultModelDomain(): RedBalanceModelDomain = RedBalanceModelDomain(
    category, widget, startDate, endDate, timeTruncate
)

fun RedMarketsTruncateModelUi.mapToRedDataTruncateModelDomain(): RedMarketsTruncateModelDomain = RedMarketsTruncateModelDomain(
    category, widget, startDate, endDate, timeTruncate, geo_limit, geo_ids
)