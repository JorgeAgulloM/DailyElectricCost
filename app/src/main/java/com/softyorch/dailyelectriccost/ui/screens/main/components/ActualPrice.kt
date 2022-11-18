/*
 * Copyright (c) 2022. File developed for DailyElectricCost App by Jorge Agulló Martín for SoftYorch
 */

package com.softyorch.dailyelectriccost.ui.screens.main.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.softyorch.dailyelectriccost.ui.model.markets.MarketsModelUi
import com.softyorch.dailyelectriccost.utils.funcExtensions.limitLengthToString

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ActualPrice(
    marketsData: MarketsModelUi
) {
    Row(
        modifier = Modifier
            .padding(top = 40.dp)
            .width(width = 380.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.weight(1f),
        ) {
            AnimatedText(
                price = marketsData.currentPrice
            ) { targetCount ->
                Text(
                    text = "${targetCount.limitLengthToString()} €",
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.End,
                    style = TextStyle(
                        fontSize = 45.sp,
                        shadow = Shadow(
                            MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                            offset = Offset(2F, 10F),
                            blurRadius = 8F
                        )
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            Text(
                text = "${marketsData.type} actual",
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodyMedium.copy(
                    shadow = Shadow(
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        offset = Offset(2F, 4F),
                        blurRadius = 2F
                    )
                )
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            LitlePrice(
                marketsData.hiPrice,
                "máximo",
                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f)
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            LitlePrice(
                marketsData.lowPrice,
                "mínimo",
                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.9f)
            )
        }
    }
}