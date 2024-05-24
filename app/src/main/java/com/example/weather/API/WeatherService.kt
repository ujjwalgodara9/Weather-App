package com.example.weather.API

import com.example.weather.database.WeatherDao
import com.example.weather.database.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {


    @GET("history.json")
    suspend fun getWeatherData(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("dt") date: String
    ): WeatherResponse


}

