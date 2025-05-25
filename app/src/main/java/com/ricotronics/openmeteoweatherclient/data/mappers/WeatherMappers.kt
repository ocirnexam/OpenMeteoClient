package com.ricotronics.openmeteoweatherclient.data.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.ricotronics.openmeteoweatherclient.data.remote.WeatherDataDto
import com.ricotronics.openmeteoweatherclient.data.remote.WeatherDto
import com.ricotronics.openmeteoweatherclient.domain.weather.WeatherData
import com.ricotronics.openmeteoweatherclient.domain.weather.WeatherDayData
import com.ricotronics.openmeteoweatherclient.domain.weather.WeatherInfo
import com.ricotronics.openmeteoweatherclient.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

private data class IndexedWeatherDayData(
    val index: Int,
    val data: WeatherDayData
)

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val weatherDataWeekMap = toWeatherDataWeekMap(weatherDataMap)
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        weatherDataPerWeek = weatherDataWeekMap,
        currentWeatherData = currentWeatherData
    )
}

private fun toWeatherDataWeekMap(weatherDataMap: Map<Int, List<WeatherData>>): List<WeatherDayData> {
    val dataList: MutableList<WeatherDayData> = mutableListOf()
    weatherDataMap.values.forEach { data ->
        val date = data.get(0).time
        var lowestTemp = Double.MAX_VALUE
        var highestTemp = Double.MIN_VALUE
        val iconRes = data.get(0).weatherType.iconRes
        data.forEach {
            if (it.temperatureCelsius < lowestTemp) {
                lowestTemp = it.temperatureCelsius
            }
            if (it.temperatureCelsius > highestTemp) {
                highestTemp = it.temperatureCelsius
            }
        }
        dataList.add(WeatherDayData(date, lowestTemp, highestTemp, iconRes))
    }
    return dataList
}