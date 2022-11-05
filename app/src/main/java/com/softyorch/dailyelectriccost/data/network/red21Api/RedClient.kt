package com.softyorch.dailyelectriccost.data.network.red21Api

import com.softyorch.dailyelectriccost.data.network.red21Api.response.Red21
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface RedClient {

    //url e.g. https://apidatos.ree.es/es/datos/balance/balance-electrico?start_date=2019-01-01T00:00&end_date=2019-01-31T23:59&time_trunc=day

    @GET("es/datos/balance/balance-electrico?start_date=2019-01-01T00:00&end_date=2019-01-31T23:59&time_trunc=day")
    suspend fun getData(): Response<Red21>

}