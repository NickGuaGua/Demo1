package com.nickgua.model.data

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("success") val isSuccessful: String,
    @SerializedName("records") val records: WeatherRecords
)

data class WeatherRecords(
    @SerializedName("datasetDescription") val description: String,
    @SerializedName("location") val data: List<WeatherInfo>
)

data class WeatherInfo(
    @SerializedName("locationName") val location: String,
    @SerializedName("weatherElement") val elements: List<WeatherElement>
)

data class WeatherElement(
    @SerializedName("elementName") val name: String,
    @SerializedName("time") val time: List<WeatherTime>
)

data class WeatherTime(
    @SerializedName("startTime") val startTime: String,
    @SerializedName("endTime") val endTime: String,
    @SerializedName("parameter") val parameter: WeatherParameter
)

data class WeatherParameter(
    @SerializedName("parameterName") val name: String,
    @SerializedName("parameterValue") val value: Int
)