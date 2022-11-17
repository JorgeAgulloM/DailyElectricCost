/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.domain.dsUseCases

data class DsUseCases(
    val saveData: SaveData,
    val getData: GetData,
    val deleteData: DeleteData
)
