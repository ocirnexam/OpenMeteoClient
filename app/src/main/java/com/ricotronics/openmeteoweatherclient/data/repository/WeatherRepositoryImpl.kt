package com.ricotronics.openmeteoweatherclient.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.ricotronics.openmeteoweatherclient.data.mappers.toWeatherInfo
import com.ricotronics.openmeteoweatherclient.data.remote.WeatherApi
import com.ricotronics.openmeteoweatherclient.domain.repository.WeatherRepository
import com.ricotronics.openmeteoweatherclient.domain.util.Resource
import com.ricotronics.openmeteoweatherclient.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}