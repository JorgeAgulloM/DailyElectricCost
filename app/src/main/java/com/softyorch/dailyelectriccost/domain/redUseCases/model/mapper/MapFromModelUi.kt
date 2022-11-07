package com.softyorch.dailyelectriccost.domain.redUseCases.model.mapper

import com.softyorch.dailyelectriccost.domain.redUseCases.model.RedDataTruncateModelDomain
import com.softyorch.dailyelectriccost.domain.redUseCases.model.RedDefaultModelDomain
import com.softyorch.dailyelectriccost.ui.model.RedDataTruncateModelUi
import com.softyorch.dailyelectriccost.ui.model.RedDefaultModelUi

fun RedDefaultModelUi.mapToRedDefaultModelDomain(): RedDefaultModelDomain = RedDefaultModelDomain(
    category, widget, startDate, endDate, timeTruncate
)

fun RedDataTruncateModelUi.mapToRedDataTruncateModelDomain(): RedDataTruncateModelDomain = RedDataTruncateModelDomain(
    category, widget, startDate, endDate, timeTruncate, geo_limit, geo_ids
)