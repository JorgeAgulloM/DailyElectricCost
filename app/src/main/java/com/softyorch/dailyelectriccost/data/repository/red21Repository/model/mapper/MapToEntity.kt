package com.softyorch.dailyelectriccost.data.repository.red21Repository.model.mapper

import com.softyorch.dailyelectriccost.data.network.red21Api.entity.RedDataTruncateEntity
import com.softyorch.dailyelectriccost.data.network.red21Api.entity.RedDefaultEntity
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedDataTruncateModel
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedDefaultModel

fun RedDefaultModel.mapToRedDefaultEntity(): RedDefaultEntity = RedDefaultEntity(
    widget, startDate, endDate, timeTruncate
)

fun RedDataTruncateModel.mapToRedDataTruncateEntity(): RedDataTruncateEntity = RedDataTruncateEntity(
    widget, startDate, endDate, timeTruncate, geo_limit, geo_ids
)