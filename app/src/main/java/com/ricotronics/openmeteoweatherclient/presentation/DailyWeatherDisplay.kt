package com.ricotronics.openmeteoweatherclient.presentation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ricotronics.openmeteoweatherclient.domain.weather.WeatherDayData
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyWeatherDisplay(
    weatherDayData: WeatherDayData,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White
) {
    val formattedTime = remember(weatherDayData.day) {
        weatherDayData.day.format(
            DateTimeFormatter.ofPattern("EE dd.MM")
        )
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = formattedTime,
            color = Color.LightGray,
        )
        Image(
            painter = painterResource(id = weatherDayData.iconRes),
            contentDescription = null,
            modifier = Modifier.width(40.dp)
        )
        Row {
            Text(
                text = "${weatherDayData.lowestTemperatureCelsius}°C",
                color = Color.Gray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = "${weatherDayData.highestTemperatureCelsius}°C",
                color = textColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}