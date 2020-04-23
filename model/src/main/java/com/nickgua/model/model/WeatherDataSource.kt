package com.nickgua.model.model

import com.nickgua.model.data.common.SingleLiveEvent
import com.nickgua.model.data.common.TaskResult

interface WeatherDataSource {
    val result: SingleLiveEvent<TaskResult<*>>
    fun getWeatherData(authCode: String, location: String?)
}