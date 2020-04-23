package com.nickgua.model

import com.nickgua.model.model.WeatherRemoteDataSource
import com.nickgua.model.model.WeatherRepository

object ModelInjector {
    fun provideWeatherRepository(): WeatherRepository {
        return WeatherRepository(provideWeatherRemoteDataSource())
    }

    private fun provideWeatherRemoteDataSource(): WeatherRemoteDataSource {
        return WeatherRemoteDataSource(provideWeatherApiService())
    }

    private fun provideWeatherApiService()
            = ApiBuilder(WeatherApiService::class.java, "https://opendata.cwb.gov.tw/api/").build()
}