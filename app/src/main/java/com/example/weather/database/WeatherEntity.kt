package com.example.weather.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class WeatherEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Int =0,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "maxtemp")
    val maxTemp: Double,

    @ColumnInfo(name = "mintemp")
    val minTemp: Double,

   // @Ignore val location: String?
)
