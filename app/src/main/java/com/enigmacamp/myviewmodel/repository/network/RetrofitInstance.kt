package com.enigmacamp.myviewmodel.repository.network

import com.enigmacamp.myviewmodel.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

abstract class RetrofitInstance {
    companion object {
        private fun getRetrofit() = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val jsonPlaceholderApi: JsonPlaceholderApi by lazy {
            getRetrofit().create(JsonPlaceholderApi::class.java)
        }
    }


}