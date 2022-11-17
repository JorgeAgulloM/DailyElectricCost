/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.core

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SendEmail @Inject constructor (private val context: Context) {
    @SuppressLint("IntentReset")
    operator fun invoke() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.data = Uri.fromParts("mailto", "agullojorge@gmail.com", null)
        intent.putExtra(Intent.EXTRA_SUBJECT, "DailyElectricCost - App")
        intent.putExtra(Intent.EXTRA_TEXT, "Hola, estoy esperando respuesta..")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("correo@gmail.com", "otrocorreo@gmail.com"))

        val sendEmail = Intent.createChooser(intent, "Open With")
        sendEmail.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        context.startActivity(Intent.createChooser(sendEmail, "DailyElectricCost - App"))
    }
}