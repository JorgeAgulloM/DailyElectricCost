package com.softyorch.dailyelectriccost

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softyorch.dailyelectriccost.data.network.red21Api.Red21Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Red21Repository): ViewModel() {

    fun getData() {
        viewModelScope.launch {
            val response = repository.getData()
            response.let {
                if (it != null) {
                    if (it.isSuccessful){
                        Log.d("RED21_VM", "data ->${it.body()}")
                    }
                }

            }
        }
    }
}