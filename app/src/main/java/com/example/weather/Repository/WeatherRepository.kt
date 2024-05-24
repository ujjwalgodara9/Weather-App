package com.example.weather.Repository

import android.util.Log
import com.example.weather.API.WeatherService
import com.example.weather.database.AverageWeather
import com.example.weather.database.WeatherDao
import com.example.weather.database.WeatherDatabase
import com.example.weather.database.WeatherEntity
import com.example.weather.database.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class WeatherRepository(private val weatherDao: WeatherDao, private val weatherService: WeatherService) {


    suspend fun insert(weather:WeatherEntity) {
        weatherDao.insertWeather(weather)
    }

    suspend fun getbydate(date: String): WeatherEntity? {
        return weatherDao.getWeatherByDate(date)
    }


    suspend fun getavg(): AverageWeather {
        return weatherDao.getAverageWeather()
    }

    suspend fun fetchWeather(date: String, location: String): WeatherEntity {

        val response = weatherService.getWeatherData("9eb8979f7ab44010b8e155415241504", location, date)


        val weather = response.forecast.forecastday.firstOrNull()?.let { forecastDay ->
            WeatherEntity(

                date = forecastDay.date,
                maxTemp = forecastDay.day.maxtemp_c,
                minTemp = forecastDay.day.mintemp_c
            )
        }

        return weather ?: throw Exception("No weather data available")
    }





}
