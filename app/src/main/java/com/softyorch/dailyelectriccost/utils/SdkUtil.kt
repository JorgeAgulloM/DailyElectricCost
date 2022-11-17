/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.utils

import android.os.Build

inline fun <T> sdk33AndUp(onSdk33: () -> T): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) onSdk33() else null

inline fun <T> sdk32AndUp(onSdk32: () -> T): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2) onSdk32() else null

inline fun <T> sdk31AndUp(onSdk31: () -> T): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) onSdk31() else null

inline fun <T> sdk30AndUp(onSdk30: () -> T): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) onSdk30() else null

inline fun <T> sdk29AndUp(onSdk29: () -> T): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) onSdk29() else null

inline fun <T> sdk28AndUp(onSdk28: () -> T): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) onSdk28() else null

inline fun <T> sdk27AndUp(onSdk27: () -> T): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) onSdk27() else null

inline fun <T> sdk26AndUp(onSdk26: () -> T): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) onSdk26() else null

inline fun <T> sdk25AndUp(onSdk25: () -> T): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) onSdk25() else null

inline fun <T> sdk24AndUp(onSdk24: () -> T): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) onSdk24() else null

inline fun <T> sdk23AndUp(onSdk23: () -> T): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) onSdk23() else null

inline fun <T> sdk22AndUp(onSdk22: () -> T): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) onSdk22() else null

val sdk33AndUp = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
val sdk32AndUp = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2
val sdk31AndUp = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
val sdk30ndUp = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
val sdk29AndUp = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
val sdk28AndUp = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
val sdk27AndUp = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1
val sdk26AndUp = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
val sdk25AndUp = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
val sdk24AndUp = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
val sdk23AndUp = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
val sdk22AndUp = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1