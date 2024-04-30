package com.alad1nks.core.network.retrofit

import com.alad1nks.core.network.NetworkDataSource
import com.alad1nks.core.network.model.BusScheduleResponse
import com.alad1nks.core.network.model.BusScheduleRevisionResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitNetworkApi {
    @GET("bus-schedule/list")
    suspend fun getBusSchedule(): BusScheduleResponse
    @GET("bus-schedule/revision")
    suspend fun getBusScheduleRevision(): BusScheduleRevisionResponse
}

private const val BASE_URL = "https://dubovozki.ru/router/"

@Singleton
class RetrofitNetwork @Inject constructor(
    networkJson: Json
) : NetworkDataSource {

    private val client = OkHttpClient.Builder().addInterceptor { chain ->
        val requestBuilder = chain.request().newBuilder()
        val response = chain.proceed(requestBuilder.build())
        if (response.code == 502) {
            throw IOException("Server is turned off")
        }
        return@addInterceptor response
    }.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()

    private val networkApi = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(RetrofitNetworkApi::class.java)

    override suspend fun getBusSchedule(): BusScheduleResponse = networkApi.getBusSchedule()
    override suspend fun getBusScheduleRevision(): BusScheduleRevisionResponse =
        networkApi.getBusScheduleRevision()

}
