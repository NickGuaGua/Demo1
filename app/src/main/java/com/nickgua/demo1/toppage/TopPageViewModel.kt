package com.nickgua.demo1.toppage

import androidx.lifecycle.Observer
import com.nickgua.demo1.common.BaseAndroidViewModel
import com.nickgua.demo1.common.PageStatus
import com.nickgua.demo1.R
import com.nickgua.demo1.common.PageEvent
import com.nickgua.demo1.common.getInfoString
import com.nickgua.demo1.navigation.Direction
import com.nickgua.model.data.WeatherElement
import com.nickgua.model.data.WeatherResponse
import com.nickgua.model.data.WeatherTime
import com.nickgua.model.data.common.TaskResult
import com.nickgua.model.model.WeatherRepository

class TopPageViewModel(private val repository: WeatherRepository) : BaseAndroidViewModel() {

    private val observer = Observer<TaskResult<*>> { result ->
        when(result) {
            is TaskResult.Success -> {
                (result.data as? WeatherResponse)?.let {
                    setPageStatus(PageStatus.Success(getMinTElement(it)?.time))
                }
            }
            is TaskResult.Error -> {
                setPageStatus(PageStatus.Error(result.throwable))
            }
        }
    }

    val onWeatherTimeClickListener: (WeatherTime) -> Unit = { time ->
        setPageEvent(PageEvent.Navigation(Direction.DetailPage(time.getInfoString())))
    }

    init {
        repository.addObserver(observer)
    }

    fun loadWeatherData() {
        setPageStatus(PageStatus.Loading)
        repository.getWeatherData(getResString(R.string.auth_code), "臺北市")
    }

    override fun refresh() {
        loadWeatherData()
    }

    private fun getMinTElement(response: WeatherResponse): WeatherElement? {
        return response.records.data.first().elements.find { it.name == "MinT" }
    }
}