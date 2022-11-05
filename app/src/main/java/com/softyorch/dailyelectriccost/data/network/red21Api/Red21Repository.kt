package com.softyorch.dailyelectriccost.data.network.red21Api

import android.util.Log
import com.softyorch.dailyelectriccost.data.network.red21Api.response.Red21
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Red21Repository @Inject constructor( private val api: RedService){
    suspend fun getData(): Response<Red21>? {
        val response = api.getData()
        Log.d("API21_REPO","response ->$response",)
        return response
    }
}