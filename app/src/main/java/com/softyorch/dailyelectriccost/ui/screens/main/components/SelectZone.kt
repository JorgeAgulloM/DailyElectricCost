/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.softyorch.dailyelectriccost.ui.screens.main.ZoneQuery
import java.util.*

@Composable
fun SelectZone(
    expanded: Boolean,
    onDismiss: () -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onDismiss() }
    ) {
        Text(
            text = "Selecciona una región",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall
        )
        Divider(modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp))
        ZoneQuery.listOfZones.forEach {
            DropdownMenuItem(
                text = {
                    Text(
                        text = it.zone.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        },
                        style = MaterialTheme.typography.labelSmall.copy(
                            textDecoration = TextDecoration.Underline
                        )
                    )
                },
                onClick = {  },
                contentPadding = PaddingValues(start = 16.dp)
            )
        }
    }
}