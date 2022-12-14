/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.utils.funcExtensions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.toDate(): Date? =
    SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).parse(this)

@SuppressLint("SimpleDateFormat")
fun Date.toDateFormattedISO8601():String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:MM")
    val currentDate = sdf.format(this)
    return currentDate.split(" ")[0] + "T" + currentDate.split(" ")[1]
}

fun String.datePickerToISO8601(): String =
    this.split("/")[2] + "-" +
            this.split("/")[1] + "-" +
            this.split("/")[0]

fun String.iSO8601ToDatePicker(): String = //2022-11-09T00:00
    this.split("-")[2].split("T")[0] + "/" +
            this.split("-")[1] + "/" +
            this.split("-")[0]

fun String.getTodayOfDate(): String =
    this.split("T")[0]

fun String.getHourOfDate(): String =
    this.split("T")[1].split(".")[0]

fun String.getHourOfNowToInt(): Int =
    this.getHourOfDate().split(":")[0].toInt()

fun String.getHourOfCalendarToInt(): Int =
    this.split(" ")[3].split(":")[0].toInt()