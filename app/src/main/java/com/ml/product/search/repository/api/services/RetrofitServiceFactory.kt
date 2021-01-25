package com.ml.product.search.repository.api.services

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject


private const val CONNECT_TIME_OUT = 20
private const val READ_TIME_OUT = 15
private const val WRITE_TIME_OUT = 15

/**
 * This class sets up API client for [Retrofit] library
 */
class RetrofitServiceFactory @Inject constructor() {

    /**
     * Creates a retrofit service from an arbitrary class
     *
     * @param classT    interface of the retrofit service
     * @param endPoint REST endpoint url
     * @return retrofit service with defined endpoint
     */
    fun <T> createRetrofitService(
        classT: Class<T>,
        endPoint: String
    ): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(endPoint)
            .addConverterFactory(GsonConverterFactory.create(getGsonBuilder()))
            .client(createHttpClient())

        return retrofit.build().create(classT)
    }

    private fun getGsonBuilder(): Gson? {
        return GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .create()
    }

    /**
     * This method to create http client
     */
    private fun createHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
            .connectTimeout(CONNECT_TIME_OUT.toLong(), TimeUnit.MINUTES)
            .readTimeout(READ_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIME_OUT.toLong(), TimeUnit.SECONDS)
        return httpClient.build()
    }
}