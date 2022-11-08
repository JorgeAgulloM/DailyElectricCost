package com.softyorch.dailyelectriccost.data.repository.red21Repository.model.mapper

import com.softyorch.dailyelectriccost.data.network.red21Api.entity.RedDataTruncateEntity
import com.softyorch.dailyelectriccost.data.network.red21Api.entity.RedDefaultEntity
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedGenerationTruncateModel
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedBalanceModel

fun RedBalanceModel.mapToRedDefaultEntity(): RedDefaultEntity = RedDefaultEntity(
    category, widget, startDate, endDate, timeTruncate
)

fun RedGenerationTruncateModel.mapToRedDataTruncateEntity(): RedDataTruncateEntity = RedDataTruncateEntity(
    category, widget, startDate, endDate, timeTruncate, geo_limit, geo_ids
)