package com.ricotronics.openmeteoweatherclient.domain.weather

data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val weatherDataPerWeek: List<WeatherDayData>,
    val currentWeatherData: WeatherData?
)
