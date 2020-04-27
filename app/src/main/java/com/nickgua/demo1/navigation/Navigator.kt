package com.nickgua.demo1.navigation

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.nickgua.demo1.toppage.TopPageFragmentDirections

class Navigator(private val fragment: Fragment) {

    private val navController: NavController = fragment.findNavController()

    fun navigate(direction: Direction) {
        when(direction) {
            is Direction.DetailPage -> {
                navController.navigate(TopPageFragmentDirections.topPageToDetail(direction.weatherTime))
            }
        }
    }

    fun toast(message: String) {
        fragment.context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }
}