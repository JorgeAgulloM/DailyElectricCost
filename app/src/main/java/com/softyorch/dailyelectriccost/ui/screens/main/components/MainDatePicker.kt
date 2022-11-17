/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.softyorch.dailyelectriccost.utils.funcExtensions.iSO8601ToDatePicker
import java.util.*

@Composable
fun MainDatePicker(date: String, onClickDatePicker: (String) -> Unit) {
    val mContext = LocalContext.current

    val mCalendar = Calendar.getInstance()
    val mYear = mCalendar.get(Calendar.YEAR)
    val mMonth = mCalendar.get(Calendar.MONTH)
    val mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()

    var mDate by remember { mutableStateOf(date.iSO8601ToDatePicker()) }

    val datePicker = DatePickerDialog(
        mContext, { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            mDate = "$dayOfMonth/${month + 1}/$year"
        },
        mYear, mMonth, mDay
    )
    datePicker.setOnDismissListener {
        if (mDate != date.iSO8601ToDatePicker()) onClickDatePicker(mDate)
    }

    datePicker.datePicker.maxDate = Date().time


    Box(modifier = Modifier.width(100.dp), contentAlignment = Alignment.Center) {
        TextButton(
            onClick = {
                datePicker.show()
            }
        ) {
            Text(text = mDate)
        }
    }

}