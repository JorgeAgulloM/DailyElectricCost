package com.softyorch.dailyelectriccost.data.network.red21Api

import com.softyorch.dailyelectriccost.data.network.red21Api.responseMercados.Red21Response
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface RedClient {

    //url e.g. https://apidatos.ree.es/es/datos/balance/balance-electrico?start_date=2019-01-01T00:00&end_date=2019-01-31T23:59&time_trunc=day
    //url e.g. https://apidatos.ree.es/en/datos/demanda/ire-general?start_date=2018-01-01T00:00&end_date=2018-12-31T23:59&time_trunc=month&geo_trunc=electric_system&geo_limit=peninsular&geo_ids=8741
    //url e.g. https://apidatos.ree.es/es/datos/generacion/estructura-generacion?start_date=2014-01-01T00:00&end_date=2018-12-31T23:59&time_trunc=year&geo_trunc=electric_system&geo_limit=ccaa&geo_ids=7

    @GET(URL)
    suspend fun getDataDefault(
        @Path("category") category: String,
        @Path("widget") widget: String = "balance-electrico",
        @Query("start_date") startDate: String = "2019-01-01T00:00",
        @Query("end_date") endDate: String = "2019-01-31T23:59",
        @Query("time_trunc") timeTruncate: String = "day"
    ): Response<Red21Response>

    @GET(URL)
    suspend fun getDataGeoTruncate(
        @Path("category") category: String,
        @Path("widget") widget: String = "balance-electrico",
        @Query("start_date") startDate: String = "2019-01-01T00:00",
        @Query("end_date") endDate: String = "2019-01-31T23:59",
        @Query("time_trunc") timeTruncate: String = "day",
        @Query("geo_trunc") geoTruncate: String = GEO_TRUNCATE,
        @Query("geo_limit") geo_limit: String = "peninsular",
        @Query("geo_ids") geo_ids: String = "8741"
    ): Response<Red21Response>

    companion object {
        const val URL = "es/datos/{category}/{widget}"
        const val GEO_TRUNCATE = "electric_system"
    }

}