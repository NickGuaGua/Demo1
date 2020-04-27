package com.nickgua.demo1.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.nickgua.demo1.R
import com.nickgua.model.data.WeatherTime
import kotlinx.android.synthetic.main.fragment_detail_page.*
import java.lang.StringBuilder

class DetailFragment : Fragment() {

    companion object {
        private const val KEY_WEATHER_TIME = "key_weather_time"

        /**
         * @param weatherTimeInfo Information string which is consists of [WeatherTime].
         */
        fun newInstance(weatherTimeInfo: String): DetailFragment {
            return DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_WEATHER_TIME, weatherTimeInfo)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getString(KEY_WEATHER_TIME)?.let {
            textMinT.text = it
        }
    }
}