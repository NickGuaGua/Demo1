package com.nickgua.demo1.common

import android.view.View
import com.nickgua.model.data.WeatherTime

fun View.showOrGone(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

inline fun <reified T> Any.isListOf(): List<T>? {
    return if (this is List<*>) {
        val filteredList = filterIsInstance<T>()
        return filteredList.takeIf { filteredList.isNotEmpty() }
    } else null
}

fun WeatherTime.getInfoString(): String {
    return StringBuilder().also {
        it.appendln(startTime)
        it.appendln(endTime)
        it.append(parameter.name)
        it.append(parameter.unit)
    }.toString()
}