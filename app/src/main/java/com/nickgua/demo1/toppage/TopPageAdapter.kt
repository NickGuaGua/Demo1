package com.nickgua.demo1.toppage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nickgua.demo1.R
import com.nickgua.demo1.common.toViewData
import com.nickgua.model.data.WeatherTime
import com.nickgua.ui.weather_item.WeatherItemView
import kotlinx.android.synthetic.main.listitem_photo.view.*
import kotlinx.android.synthetic.main.listitem_weather_time.view.*

class TopPageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_WEATHER_DATA = 0
        private const val TYPE_PHOTO = 1
        private const val INTERVAL_SIZE = 2
    }

    var onWeatherTimeClickListener: ((WeatherTime) -> Unit)? = null

    private var weatherTimes = listOf<WeatherTime>()

    override fun getItemViewType(position: Int): Int {
        return if (position % INTERVAL_SIZE == 0) TYPE_WEATHER_DATA else TYPE_PHOTO
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            TYPE_WEATHER_DATA -> {
                WeatherTimeViewHolder(
                    inflater.inflate(R.layout.listitem_weather_time, parent, false)
                )
            }
            TYPE_PHOTO -> {
                PhotoViewHolder(
                    inflater.inflate(R.layout.listitem_photo, parent, false)
                )
            }
            else -> throw IllegalArgumentException("This view type: $viewType is undefined.")
        }
    }

    override fun getItemCount(): Int {
        return INTERVAL_SIZE * weatherTimes.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is WeatherTimeViewHolder -> {
                with(weatherTimes[position / INTERVAL_SIZE]) {
                    holder.weatherItemView.setData(this.toViewData())
                    holder.itemView.setOnClickListener { onWeatherTimeClickListener?.invoke(this) }
                }
            }
            is PhotoViewHolder -> {
                holder.photo.setImageResource(R.drawable.ic_launcher_foreground)
            }
        }
    }

    fun setWeatherTimes(weatherTimes: List<WeatherTime>) {
        this.weatherTimes = weatherTimes
        notifyDataSetChanged()
    }

    class WeatherTimeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val weatherItemView: WeatherItemView = itemView.weatherItemView
    }

    class PhotoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.photo
    }
}