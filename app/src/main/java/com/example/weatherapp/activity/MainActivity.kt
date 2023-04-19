package com.example.weatherapp.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.model.WeatherUIState
import com.example.weatherapp.utils.logD
import com.example.weatherapp.viewmodel.WeatherListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private val viewModel: WeatherListViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private val weatherListAdapter by lazy {
        WeatherListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        logD("onCreate")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        observeUIChanges()
    }

    private fun initRecyclerView() {
        logD("initRecyclerView")
        binding.weatherListRv.apply {
            adapter = weatherListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun observeUIChanges() {
        viewModel.uiState.observe(this) {
            when (it) {
                is WeatherUIState.Loading -> {
                    handleLoadingWheelVisibility(true)
                    handleWeatherListVisibility(false)
                    handleErrorTextVisibility(false)
                }
                is WeatherUIState.Error -> {
                    handleErrorTextVisibility(true)
                    handleLoadingWheelVisibility(false)
                    handleWeatherListVisibility(false)
                }
                is WeatherUIState.ShowWeatherAlerts -> {
                    weatherListAdapter.setItems(it.weatherAlerts.features)
                    handleWeatherListVisibility(true)
                    handleErrorTextVisibility(false)
                    handleLoadingWheelVisibility(false)
                }
            }
        }
    }

    private fun handleWeatherListVisibility(visibility: Boolean) {
        binding.weatherListRv.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    private fun handleLoadingWheelVisibility(visibility: Boolean) {
        binding.loadingWheelPb.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    private fun handleErrorTextVisibility(visibility: Boolean) {
        binding.errorTextTv.visibility = if (visibility) View.VISIBLE else View.GONE
    }
}