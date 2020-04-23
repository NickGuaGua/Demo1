package com.nickgua.model

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiBuilder <T>(
    private var service: Class<T>,
    private var url: String,
    private var client: OkHttpClient = OkHttpClient()
) {

    fun build(): T = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(url)
        .client(client)
        .build().create(service)
}