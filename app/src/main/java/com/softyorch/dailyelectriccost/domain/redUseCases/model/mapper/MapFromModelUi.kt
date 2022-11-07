package com.softyorch.dailyelectriccost.domain.redUseCases.model.mapper

import com.softyorch.dailyelectriccost.domain.redUseCases.model.RedDataTruncateModelDomain
import com.softyorch.dailyelectriccost.domain.redUseCases.model.RedDefaultModelDomain
import com.softyorch.dailyelectriccost.model.RedDataTruncateModelUi
import com.softyorch.dailyelectriccost.model.RedDefaultModelUi

fun RedDefaultModelUi.mapToRedDefaultModelDomain(): RedDefaultModelDomain = RedDefaultModelDomain(
    widget, startDate, endDate, timeTruncate
)

fun RedDataTruncateModelUi.mapToRedDataTruncateModelDomain(): RedDataTruncateModelDomain = RedDataTruncateModelDomain(
    widget, startDate, endDate, timeTruncate, geo_limit, geo_ids
)