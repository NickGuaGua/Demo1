package com.nickgua.demo1.navigation

import androidx.fragment.app.Fragment
import com.nickgua.demo1.R
import com.nickgua.demo1.detail.DetailFragment

class Navigator (private val fragment: Fragment) {

    fun navigate(direction: Direction) {
        when(direction) {
            is Direction.DetailPage -> {
                fragment.activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.container, DetailFragment.newInstance(direction.weatherTime))
                    ?.addToBackStack(null)
                    ?.commit()
            }
        }
    }
}