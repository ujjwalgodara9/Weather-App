package com.example.weather.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(vararg weatherEntity: WeatherEntity)

    @Query("SELECT * FROM weather_table WHERE date = (:date)")
    suspend fun getWeatherByDate(date: String): WeatherEntity?

    @Query("SELECT AVG(maxtemp) as averageMaxTemp, AVG(mintemp) as averageMinTemp FROM weather_table")
    suspend fun getAverageWeather(): AverageWeather

}

