package com.alad1nks.core.network.retrofit

import com.alad1nks.core.network.NetworkDataSource
import com.alad1nks.core.network.model.BusScheduleResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitNetworkApi {
    @GET(value = "bus-schedule")
    suspend fun getBusSchedule(): BusScheduleResponse
}

private const val BASE_URL = "https://dubovozki.ru/router"

@Singleton
class RetrofitNetwork @Inject constructor(
    networkJson: Json,
) : NetworkDataSource {
    private val networkApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(RetrofitNetworkApi::class.java)

    override suspend fun getBusSchedule(): BusScheduleResponse = networkApi.getBusSchedule()
}
