package com.nickgua.demo1.navigation

sealed class Direction {
    data class DetailPage(val weatherTime: String): Direction()
}