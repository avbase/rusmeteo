Android library for obtaining rusmeteo.net forecast
# Android library rusmeteo:
Android library for obtaining rusmeteo.net forecast.

Unfortunately rusmeteo.net does not produce JSON format, only XML, I hope in the future it will be fixed.

[![platform](https://img.shields.io/badge/platform-Android-yellow.svg)](https://www.android.com)
[![Kotlin version](https://img.shields.io/badge/Kotlin-1.7.20-blue)]([https://kotlinlang.org/docs/whatsnew16.html](https://kotlinlang.org/docs/whatsnew1720.html))
[![mavenCentral](https://img.shields.io/badge/download_Maven-v1.0.0-red)](https://search.maven.org/search?q=ru.avicorp.rusmeteolibrary)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

### Implementation of the library of rusmeteolibrary in the my project of the smart home.
<img align="center" src="https://github.com/avbase/rusmeteo/blob/main/rusmeteolibrary.gif" height="400" style="max-width: 50%; display: inline-block;" data-target="animated-image.originalImage">


###  Capabilities rusmeteoLibrary: 


For hourly and daily 10-day weather data.

    collection: rusForecastClockData on 24 hours

    - weather 
    - current temperature (°C)
    - precipitation (%)
    - humidity (%) 
    - wind (m\s)
    - wind direction
    - pressure (mm)



    collection: rusForecastDayData on 10 days

    - day of the week
    - month 
    - daytime temperature (°C)
    - night temperature (°C) 
    - weather 
    - precipitation 
    - wind (m\s)
    - night temperature (°C)
    - morning temperature (°C)
    - daytime temperature (°C)
    - evening temperature (°C)


## How to use

The project's Maven access is hosted on OSS Sonatype (and available from Maven Central).
Android : rusmeteolibrary supports Android starting on API 26 and up.

#### Using with Gradle

Add the dependencies into the project's build.gradle:
```groovy
repositories {
  mavenCentral()
}

dependencies {
       implementation "ru.avicorp:rusmeteolibrary:1.0.0"
}
```

#### Example

!!! Please check permission INTERNET



```groovy

val rusmeteoWeather = RusmeteoWeather()//get to rusmeteolibrary instance

        //!!! QUERY FORMAT (LOCALITY) SPECIFY ON RUSMETEO.NET !!!
        //request to receive data from the resource rusmeteo.net (hour forecast)
        rusmeteoWeather.rusForecastClockData =
            rusmeteoWeather.loadClockForecastData("https://rusmeteo.net/weather/sergiev-posad/hourly/")
        //request to receive data from the resource rusmeteo.net (days forecast)
        rusmeteoWeather.rusForecastDayData =
            rusmeteoWeather.loadDayForecastData("https://rusmeteo.net/weather/sergiev-posad/10days/")

```
#### Hour forecast
```groovy
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
```
#### 10 days forecast
```groovy
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
```



### Usage description

The library uses corutins to appeal to rusmeteo.net, the accessibility check lies with the user, my implementation of this library is used for the smart home project [Smart Home] (https://avicorp.ru), see the picture above.

### License

 Copyright (C) 2022 The avicorp Authors

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
