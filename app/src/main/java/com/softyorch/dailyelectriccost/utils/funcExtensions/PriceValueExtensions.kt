/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.utils.funcExtensions

fun Double.limitLengthToString(): String =
    if (this.toString().length > 7) {
        this.toString().substring(0, 7)
    } else this.toString()

fun Float.limitLengthToString(): String =
    if (this.toString().length > 7) {
        this.toString().substring(0, 7)
    } else this.toString()