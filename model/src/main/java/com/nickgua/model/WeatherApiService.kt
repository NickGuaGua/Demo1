package com.nickgua.model

import com.nickgua.model.data.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApiService {

    @Headers("accept: application/json")
    @GET("v1/rest/datastore/F-C0032-001")
    fun getNewHitsPlaylists(
        @Query("Authorization") authorization: String,
        @Query("locationName") location: String
    ): Call<WeatherResponse>
}