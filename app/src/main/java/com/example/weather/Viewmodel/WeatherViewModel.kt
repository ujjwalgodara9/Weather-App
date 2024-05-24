package com.example.weather.Viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.API.RetrofitInstance
import com.example.weather.API.WeatherService
import com.example.weather.Repository.WeatherRepository
import com.example.weather.database.AverageWeather
import com.example.weather.database.WeatherDatabase
import com.example.weather.database.WeatherEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val _weatherData = MutableLiveData<WeatherEntity>()
    val weatherData: LiveData<WeatherEntity> = _weatherData
    private val repository: WeatherRepository

    init {
        val dao = WeatherDatabase.getDatabase(application).weatherDao()
        val api = RetrofitInstance.api
        repository = WeatherRepository(dao,api)
    }

    private fun postWeatherData(weather: WeatherEntity) {
        _weatherData.postValue(weather)
    }

    fun insert(weather: WeatherEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(weather)
    }

    fun fetchWeather(date: String, location: String) {
        viewModelScope.launch {
            try {
                val weather = repository.fetchWeather(date, location)
                _weatherData.postValue(weather)
                Log.d("tag","weather inserting in database by fetching from api")
                insert(weather)
                Log.d("tag","weather inserted in database by fetching from api")
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Error fetching weather data", e)
            }
        }
    }

    fun getbydate(date: String) {
        viewModelScope.launch {
            repository.getbydate(date)?.let {
                _weatherData.postValue(it)
            }
        }
    }

    private fun postWeather(weather: AverageWeather) {
        _weatherData.postValue(WeatherEntity(date="10-10-1000", maxTemp = weather.averageMaxTemp, minTemp = weather.averageMinTemp))
    }

    fun getavg() {
        viewModelScope.launch {
            repository.getavg()?.let {
                postWeather(it)
            }
        }
    }

}

