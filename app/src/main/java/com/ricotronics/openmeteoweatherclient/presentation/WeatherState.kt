package com.ricotronics.openmeteoweatherclient.presentation

import com.ricotronics.openmeteoweatherclient.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
