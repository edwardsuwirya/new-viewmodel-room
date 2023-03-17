package com.enigmacamp.myviewmodel.repository.network

import com.enigmacamp.myviewmodel.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

abstract class RetrofitInstance {
    companion object {
        private val interceptor = HttpLoggingInterceptor().apply {
            apply { level = HttpLoggingInterceptor.Level.BODY }
        }
        private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        private fun getRetrofit() = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()

        val jsonPlaceholderApi: JsonPlaceholderApi by lazy {
            getRetrofit().create(JsonPlaceholderApi::class.java)
        }
    }


}