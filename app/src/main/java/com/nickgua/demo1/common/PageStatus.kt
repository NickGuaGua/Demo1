package com.nickgua.demo1.common

sealed class PageStatus {
    object Loading: PageStatus()
    data class Success<T>(val data: T) : PageStatus()
    data class Error(val throwable: Throwable) : PageStatus()
}