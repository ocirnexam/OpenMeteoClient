package com.ricotronics.openmeteoweatherclient.data.remote

import com.squareup.moshi.Json

data class WeatherDto(
    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto
)