package com.softyorch.dailyelectriccost.data.network.red21Api

import android.util.Log
import com.softyorch.dailyelectriccost.data.network.red21Api.response.Red21
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.internal.notify
import retrofit2.Response
import javax.inject.Inject

class RedService @Inject constructor(private val redClient: RedClient) {
    suspend fun getData(): Response<Red21>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = redClient.getData()
                Log.d("API21", "Response ->$response")
                response
            } catch (ex: java.lang.Exception) {
                ex.message.toString().notify()
                null
            }
        }
    }
}