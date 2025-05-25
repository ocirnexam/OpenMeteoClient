package com.ricotronics.openmeteoweatherclient.domain.weather

import java.time.LocalDateTime

class WeatherDayData(
    val day: LocalDateTime,
    val lowestTemperatureCelsius: Double,
    val highestTemperatureCelsius: Double,
    val iconRes: Int
)