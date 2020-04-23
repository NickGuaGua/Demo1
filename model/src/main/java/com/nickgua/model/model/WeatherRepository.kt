package com.nickgua.model.model

import androidx.lifecycle.Observer
import com.nickgua.model.data.common.TaskResult

class WeatherRepository(private val dataSource: WeatherDataSource) {

    private val result = dataSource.result

    fun getWeatherData(authCode: String, location: String? = null) {
        dataSource.getWeatherData(authCode, location)
    }

    fun addObserver(observer: Observer<TaskResult<*>>) {
        result.observeForever(observer)
    }

    fun removeObserver(observer: Observer<TaskResult<*>>) {
        result.removeObserver(observer)
    }
}