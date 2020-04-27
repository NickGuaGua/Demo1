package com.nickgua.ui.weather_item

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.nickgua.ui.R
import kotlinx.android.synthetic.main.view_weather_item.view.*

class WeatherItemView : ConstraintLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    companion object {
        private const val KEY_SAVED_STATE = "key_saved_state"
        private const val KEY_VIEW_DATA = "key_view_data"
    }

    private var viewData: WeatherItemViewData? = null

    private val itemView = LayoutInflater.from(context).inflate(R.layout.view_weather_item, this, true)

    fun setData(weatherItemViewData: WeatherItemViewData) {
        viewData = weatherItemViewData
        updateUI(weatherItemViewData)
    }

    private fun updateUI(weatherItemViewData: WeatherItemViewData) {
        itemView.textMinT.text = weatherItemViewData.minT
    }

    override fun onSaveInstanceState(): Parcelable? {
        return Bundle().apply {
            putParcelable(KEY_SAVED_STATE, super.onSaveInstanceState())
            putParcelable(KEY_VIEW_DATA, viewData)
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is Bundle) {
            val superState: Parcelable? = state.getParcelable(KEY_SAVED_STATE)
            val viewData: WeatherItemViewData? = state.getParcelable(KEY_VIEW_DATA)
            updateUI(viewData ?: return)
            super.onRestoreInstanceState(superState)
        } else {
            super.onRestoreInstanceState(state)
        }
    }
}