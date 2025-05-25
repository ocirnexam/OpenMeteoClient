package com.ricotronics.openmeteoweatherclient.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun WeatherAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = androidx.compose.material3.Typography(),
        shapes = Shapes,
        content = content
    )
}