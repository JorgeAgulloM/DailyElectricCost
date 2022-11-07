package com.softyorch.dailyelectriccost.ui.screens.main

import androidx.lifecycle.ViewModel
import com.softyorch.dailyelectriccost.domain.redUseCases.RedUsesCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val redUsesCases: RedUsesCases) : ViewModel() {

}