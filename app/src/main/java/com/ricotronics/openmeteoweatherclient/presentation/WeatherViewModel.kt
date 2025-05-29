package com.ricotronics.openmeteoweatherclient.presentation


import android.location.Address
import android.location.Geocoder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricotronics.openmeteoweatherclient.domain.location.LocationTracker
import com.ricotronics.openmeteoweatherclient.domain.repository.WeatherRepository
import com.ricotronics.openmeteoweatherclient.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
): ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    var latitude: Double? = null
        private set
    var longitude: Double? = null
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->
                when(val result = repository.getWeatherData(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        state = state.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
                latitude = location.latitude
                longitude = location.longitude
            } ?: kotlin.run {
                state = state.copy(
                    weatherInfo = null,
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS. :')"
                )
            }
        }
    }
}