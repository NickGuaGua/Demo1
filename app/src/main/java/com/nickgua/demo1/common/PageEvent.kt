package com.nickgua.demo1.common

import com.nickgua.demo1.navigation.Direction

sealed class PageEvent {
    data class Navigation(val direction: Direction): PageEvent()
}