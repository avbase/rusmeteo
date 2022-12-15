package ru.avicorp.checkrusmeteolibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.avicorp.rusmeteoweather.usecases.RusmeteoWeather

//Android library for obtaining rusmeteo.net forecast
//by avicorp.ru 15.12.2022

//!!! Please check permission INTERNET
//Unfortunately rusmeteo.net does not produce JSON format, only XML, I hope in the future it will be fixed

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val rusmeteoWeather = RusmeteoWeather()//get to rusmeteolibrary instance

        //!!! QUERY FORMAT (LOCALITY) SPECIFY ON RUSMETEO.NET !!!
        //request to receive data from the resource rusmeteo.net (hour forecast)
        rusmeteoWeather.rusForecastClockData =
            rusmeteoWeather.loadClockForecastData("https://rusmeteo.net/weather/sergiev-posad/hourly/")
        //request to receive data from the resource rusmeteo.net (days forecast)
        rusmeteoWeather.rusForecastDayData =
            rusmeteoWeather.loadDayForecastData("https://rusmeteo.net/weather/sergiev-posad/10days/")

        //Validate data acquisition and output (hour forecast)
        if (rusmeteoWeather.checkValidClockData()) {
            rusmeteoWeather.rusForecastClockData.forEachIndexed { i, _ ->
                with(rusmeteoWeather.rusForecastClockData[i]) {
                    Log.e(
                        "Forecast clock",
                        "clock ${dateClock}" +
                                ",weather ${weatherName}" +
                                ", current temp ${tempNightDay}°C" +
                                ", precipitation ${precipPercent}%" +
                                ", humidity ${humidityPercent}%" +
                                ", wind ${windSpeed}m/s" +
                                ", wind direction ${windDirection}" +
                                ", pressure ${pressureData} mm "
                    )
                }
            }
        }
        //Validate data acquisition and output (10-day forecast)
        if (rusmeteoWeather.checkValidDayData()) {
            rusmeteoWeather.rusForecastDayData.forEachIndexed { i, _ ->
                with(rusmeteoWeather.rusForecastDayData[i]) {
                    Log.e(
                        "Forecast days",
                        "day of the week ${weekDay}" +
                                ", month ${dateMonth}" +
                                ", daytime temp ${tempDay}°C" +
                                ", night temp ${tempNight}°C" +
                                ", weather ${weatherName}" +
                                ", precipitation ${precipPercent}%" +
                                ", wind ${windSpeed}m/s" +
                                ", night temp ${temperatureNight}°C" +
                                ", morning temp ${temperatureMorning}°C" +
                                ", daytime temp ${temperatureDay}°C" +
                                ", evening temp ${temperatureEvening}°C"
                    )
                }
            }
        }

    }

}
