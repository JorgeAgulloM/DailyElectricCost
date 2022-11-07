package com.softyorch.dailyelectriccost.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.softyorch.dailyelectriccost.data.network.red21Api.responseMercados.Red21Mercados

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {

    Scaffold(
    ) {

        val dataDefault: Red21Mercados? by viewModel.defaultData.observeAsState(initial = Red21Mercados.red21MercadosEmpty)

        Column(
            modifier = Modifier.padding(it.calculateTopPadding()).fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = dataDefault?.included?.get(0)?.attributes?.title ?: "null")
            Text(text = dataDefault?.included?.get(0)?.attributes?.description?.toString() ?: "null")
            Text(text = dataDefault?.included?.get(0)?.attributes?.magnitude ?: "null")
            Text(text = dataDefault?.included?.get(0)?.attributes?.lastUpdate ?: "null")
            Text(text = dataDefault?.included?.get(0)?.attributes?.type?.toString() ?: "null")
            Text(text = dataDefault?.included?.get(0)?.attributes?.composite?.toString() ?: "null")
            Text(text = dataDefault?.included?.get(0)?.attributes?.color ?: "null")
            Text(text = dataDefault?.included?.get(0)?.attributes?.values?.get(0)?.value?.toString() ?: "null")
        }
    }
}