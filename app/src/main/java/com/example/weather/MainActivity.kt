package com.example.weather

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weather.Viewmodel.WeatherViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: WeatherViewModel

    private lateinit var dateInput: EditText
    private lateinit var locationInput: EditText
    private lateinit var searchButton: Button
    private lateinit var weatherInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dateInput = findViewById(R.id.dateInput)
        locationInput = findViewById(R.id.locationInput)
        searchButton = findViewById(R.id.searchButton)
        weatherInfo = findViewById(R.id.tvWeatherInfo)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[WeatherViewModel::class.java]

        searchButton.setOnClickListener {
            val inputDate = dateInput.text.toString()
            val inputLocation = locationInput.text.toString()

            if (!isValidDate(inputDate)) {
                Toast.makeText(this, "Please enter a valid date in YYYY-MM-DD format.", Toast.LENGTH_LONG).show()
            } else {
                val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

                if (inputDate > currentDate) {
                    Log.d("tag", "Input date is greater than today's date, calling getavg()")
                    viewModel.getavg()
                } else {
                    if (ConnectionUtils.isNetworkAvailable(this)) {
                        Log.d("tag", "from api")
                        viewModel.fetchWeather(inputDate, inputLocation)
                    } else {
                        Log.d("tag", "from database")
                        viewModel.getbydate(inputDate)
                    }
                }
            }
        }

        viewModel.weatherData.observe(this, Observer { weather ->
            // Update the UI when the weather data changes
            Log.d("tag","updating ui")
            weatherInfo.text = "Max Temp: ${weather.maxTemp}, Min Temp: ${weather.minTemp}"
            Log.d("tag","updated ui")
        })
    }

    fun isValidDate(date: String): Boolean {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        dateFormat.isLenient = false
        try {
            val parsedDate = dateFormat.parse(date) ?: return false
            val parts = date.split("-")
            if (parts.size == 3) {
                val year = parts[0].toInt()
                val month = parts[1].toInt()
                val day = parts[2].toInt()

                if (month !in 1..12 || day !in 1..31) {
                    return false
                }

                // Check for the correct number of days in each month considering leap years
                val cal = Calendar.getInstance()
                cal.time = parsedDate
                if (day > cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    return false
                }
            }
        } catch (e: ParseException) {
            return false
        }
        return true
    }
}
