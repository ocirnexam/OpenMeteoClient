package com.ricotronics.openmeteoweatherclient.domain.repository

import com.ricotronics.openmeteoweatherclient.domain.util.Resource
import com.ricotronics.openmeteoweatherclient.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}