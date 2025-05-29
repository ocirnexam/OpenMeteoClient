package com.ricotronics.openmeteoweatherclient.presentation


import android.Manifest
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ricotronics.openmeteoweatherclient.presentation.ui.theme.DeepBlue
import com.ricotronics.openmeteoweatherclient.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import com.ricotronics.openmeteoweatherclient.R
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo()
        }
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ))
        setContent {
            var background = R.drawable.sunny_sky
            viewModel.state.weatherInfo?.currentWeatherData?.weatherType?.let { background = it.backgroundRes }
            val addresses: List<Address>
            var city: String = ""
            val geocoder = Geocoder(this, Locale.getDefault())
            viewModel.latitude?.let {
                addresses = geocoder.getFromLocation(
                    viewModel.latitude!!,
                    viewModel.longitude!!,
                    1
                )!!
                city = addresses[0].locality
            }


            WeatherAppTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .paint(
                            painterResource(background),
                            contentScale = ContentScale.FillHeight
                        )
                        .padding(vertical = 30.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        item {
                            WeatherCard(
                                state = viewModel.state,
                                city = city,
                                backgroundColor = DeepBlue
                            )
                        }
                        item {
                            WeatherForecast(state = viewModel.state)
                        }
                        item {
                            WeatherWeeklyForecast(state = viewModel.state)
                        }
                    }
                    if(viewModel.state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    viewModel.state.error?.let { error ->
                        Text(
                            text = error,
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}