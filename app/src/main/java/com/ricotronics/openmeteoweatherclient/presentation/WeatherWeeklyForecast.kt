package com.ricotronics.openmeteoweatherclient.presentation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ricotronics.openmeteoweatherclient.presentation.ui.theme.DarkBlue
import com.ricotronics.openmeteoweatherclient.presentation.ui.theme.DeepBlue

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherWeeklyForecast(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    state.weatherInfo?.weatherDataPerWeek?.let { data ->
        Card (
            colors = CardColors(containerColor = DarkBlue, contentColor = Color.White, disabledContentColor = Color.White, disabledContainerColor = DarkBlue),
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Next 7 Days",
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(content = {
                    items(data) { weatherDayData ->
                        DailyWeatherDisplay(
                            weatherDayData = weatherDayData,
                            modifier = Modifier
                                .height(100.dp)
                                .padding(horizontal = 16.dp)
                        )
                    }
                })
            }
        }
    }
}