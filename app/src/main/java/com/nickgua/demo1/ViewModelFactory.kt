package com.nickgua.demo1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nickgua.demo1.toppage.TopPageViewModel
import com.nickgua.model.ModelInjector
import java.lang.IllegalArgumentException

class ViewModelFactory: ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(TopPageViewModel::class.java) -> {
                    TopPageViewModel(ModelInjector.provideWeatherRepository())
                }
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
    }
}