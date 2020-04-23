package com.nickgua.model.model

import com.nickgua.model.WeatherApiService
import com.nickgua.model.data.common.RequestFlag
import com.nickgua.model.data.common.SingleLiveEvent
import com.nickgua.model.data.common.TaskResult
import retrofit2.Call
import retrofit2.Response

class WeatherRemoteDataSource(private val apiService: WeatherApiService) : WeatherDataSource {
    override val result = SingleLiveEvent<TaskResult<*>>()

    override fun getWeatherData(authCode: String, location: String?) {
        apiService
            .getNewHitsPlaylists(authCode, location ?: "")
            .enqueue(getRetrofit2Callback(RequestFlag.GET_WEATHER_DATA))
    }

    private fun <T> getRetrofit2Callback(flag: RequestFlag) = object : retrofit2.Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            setApiResponse(TaskResult.Error(t as Exception, flag))
        }

        override fun onResponse(call: Call<T>, response: Response<T>?) {
            response?.body()?.let {
                setApiResponse(TaskResult.Success(it, flag))
                return
            }

            response?.errorBody()?.let {
                val errorString = it.string()
                setApiResponse(TaskResult.Error(Exception(errorString), flag))
            }
        }
    }

    private fun setApiResponse(response: TaskResult<*>) {
        result.postValue(response)
    }
}