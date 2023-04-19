package com.example.weatherapp.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.WeatherAlertItemLayoutBinding
import com.example.weatherapp.model.WeatherAlert
import com.example.weatherapp.utils.formatDate
import com.example.weatherapp.utils.logD

class WeatherListAdapter : RecyclerView.Adapter<WeatherListAdapter.WeatherAlertVH>(){

    private val weatherAlertsList = mutableListOf<WeatherAlert>()

    fun setItems(alertsList: List<WeatherAlert>) {
        if (alertsList.isEmpty()) {
            logD("setItems: alertsList is empty")
        } else {
            weatherAlertsList.clear()
            weatherAlertsList.addAll(alertsList)
            notifyDataSetChanged()
        }
    }

    fun getItem(position: Int): WeatherAlert? {
        if (weatherAlertsList.isEmpty()) return null

        return when (val realPosition = position % weatherAlertsList.size) {
            in 0 until weatherAlertsList.size -> weatherAlertsList[realPosition]
            else -> null
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAlertVH {
        val binding = WeatherAlertItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherAlertVH(binding)
    }

    override fun onBindViewHolder(holder: WeatherAlertVH, position: Int) {
        getItem(position)?.let {
            holder.bindItem(it)
        }
    }

    override fun getItemCount(): Int = weatherAlertsList.size

    inner class WeatherAlertVH(private val binding: WeatherAlertItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindItem(weatherAlert: WeatherAlert) {
            weatherAlert.properties.also { weatherAlertProperties ->
                binding.apply {
                    eventNameTv.text = weatherAlertProperties.event
                    eventSourceTv.text = weatherAlertProperties.senderName
                    eventPeriodTv.text = weatherAlertProperties.formatDate(binding.root.context)
                    loadImage(pictureIv)
                }
            }
        }

        private fun loadImage(imageView: ImageView) {
            val imageId = adapterPosition
            val imageUrl = "https://picsum.photos/id/$imageId/300/300"
            Glide.with(imageView.context)
                .load(imageUrl)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .into(imageView)
        }

    }
}