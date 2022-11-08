package com.softyorch.dailyelectriccost.data.repository.red21Repository.model.mapper

import com.softyorch.dailyelectriccost.data.network.red21Api.entity.RedMarketsTruncateEntity
import com.softyorch.dailyelectriccost.data.network.red21Api.entity.RedBalanceEntity
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedMarketsTruncateModel
import com.softyorch.dailyelectriccost.data.repository.red21Repository.model.RedBalanceModel

fun RedBalanceModel.mapToRedBalanceEntity(): RedBalanceEntity = RedBalanceEntity(
    category, widget, startDate, endDate, timeTruncate
)

fun RedMarketsTruncateModel.mapToRedMarketsTruncateEntity(): RedMarketsTruncateEntity = RedMarketsTruncateEntity(
    category, widget, startDate, endDate, timeTruncate, geo_limit, geo_ids
)