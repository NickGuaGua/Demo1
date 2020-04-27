package com.nickgua.demo1.common

import com.nickgua.model.data.WeatherTime
import com.nickgua.ui.weather_item.WeatherItemViewData

fun WeatherTime.toViewData(): WeatherItemViewData {
    return WeatherItemViewData(this.getInfoString())
}