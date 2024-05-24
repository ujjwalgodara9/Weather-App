package com.example.weather.database

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [WeatherEntity::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

//        @Synchronized
        fun getDatabase(context: Context): WeatherDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "weather_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

//        @Synchronized
//        fun getDatabase(ctx: Context): WeatherDatabase {
//            if(instance == null)
//                instance = Room.databaseBuilder(ctx.applicationContext, WeatherDatabase::class.java,
//                    "weather_database")
//                    .fallbackToDestructiveMigration()
//                    .build()
//
//            return instance!!
//
//        }
    }
}
