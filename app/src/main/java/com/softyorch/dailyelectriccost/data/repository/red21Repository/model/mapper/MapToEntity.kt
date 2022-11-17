package com.softyorch.dailyelectriccost.data.repository.red21Repository.model.mapper

import com.softyorch.dailyelectriccost.data.network.red21Api.entity.RedMarketsTruncateEntity
import com.softyorch.dailyelectriccost.data.network.red21Api.entity.RedBalanceEntity
import com.softyorch.dailyelectriccost.data.network.red21Api.entity.RedGenerationTruncateEntity
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedMarketsTruncateModel
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedBalanceModel
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedGenerationTruncateModel

fun RedBalanceModel.mapToRedBalanceEntity(): RedBalanceEntity = RedBalanceEntity(
    category, widget, startDate, endDate, timeTruncate
)

fun RedGenerationTruncateModel.mapToGenerationTruncateEntity(): RedGenerationTruncateEntity = RedGenerationTruncateEntity(
    category, widget, startDate, endDate, timeTruncate, geo_limit, geo_ids
)

fun RedMarketsTruncateModel.mapToRedMarketsTruncateEntity(): RedMarketsTruncateEntity = RedMarketsTruncateEntity(
    category, widget, startDate, endDate, timeTruncate, geo_limit, geo_ids
)