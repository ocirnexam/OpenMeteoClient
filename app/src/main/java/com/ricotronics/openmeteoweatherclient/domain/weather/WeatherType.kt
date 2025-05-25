package com.ricotronics.openmeteoweatherclient.domain.weather


import androidx.annotation.DrawableRes
import com.ricotronics.openmeteoweatherclient.R

sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val iconRes: Int,
    @DrawableRes val backgroundRes: Int
) {
    object ClearSky : WeatherType(
        weatherDesc = "Clear sky",
        iconRes = R.drawable.ic_sunny,
        backgroundRes = R.drawable.sunny_sky
    )
    object MainlyClear : WeatherType(
        weatherDesc = "Mainly clear",
        iconRes = R.drawable.ic_sunnycloudy,
        backgroundRes = R.drawable.partly_cloudy
    )
    object PartlyCloudy : WeatherType(
        weatherDesc = "Partly cloudy",
        iconRes = R.drawable.ic_sunnycloudy,
        backgroundRes = R.drawable.partly_cloudy
    )
    object Overcast : WeatherType(
        weatherDesc = "Overcast",
        iconRes = R.drawable.ic_cloudy,
        backgroundRes = R.drawable.cloudy_sky
    )
    object Foggy : WeatherType(
        weatherDesc = "Foggy",
        iconRes = R.drawable.ic_very_cloudy,
        backgroundRes = R.drawable.cloudy_sky
    )
    object DepositingRimeFog : WeatherType(
        weatherDesc = "Depositing rime fog",
        iconRes = R.drawable.ic_very_cloudy,
        backgroundRes = R.drawable.cloudy_sky
    )
    object LightDrizzle : WeatherType(
        weatherDesc = "Light drizzle",
        iconRes = R.drawable.ic_sunnyrainy,
        backgroundRes = R.drawable.rainy_sky
    )
    object ModerateDrizzle : WeatherType(
        weatherDesc = "Moderate drizzle",
        iconRes = R.drawable.ic_rainshower,
        backgroundRes = R.drawable.rainy_sky

    )
    object DenseDrizzle : WeatherType(
        weatherDesc = "Dense drizzle",
        iconRes = R.drawable.ic_rainshower,
        backgroundRes = R.drawable.rainy_sky
    )
    object LightFreezingDrizzle : WeatherType(
        weatherDesc = "Slight freezing drizzle",
        iconRes = R.drawable.ic_snowyrainy,
        backgroundRes = R.drawable.rainy_sky
    )
    object DenseFreezingDrizzle : WeatherType(
        weatherDesc = "Dense freezing drizzle",
        iconRes = R.drawable.ic_snowyrainy,
        backgroundRes = R.drawable.snowy_sky
    )
    object SlightRain : WeatherType(
        weatherDesc = "Slight rain",
        iconRes = R.drawable.ic_rainy,
        backgroundRes = R.drawable.rainy_sky
    )
    object ModerateRain : WeatherType(
        weatherDesc = "Rainy",
        iconRes = R.drawable.ic_rainy,
        backgroundRes = R.drawable.rainy_sky
    )
    object HeavyRain : WeatherType(
        weatherDesc = "Heavy rain",
        iconRes = R.drawable.ic_rainy,
        backgroundRes = R.drawable.rainy_sky
    )
    object HeavyFreezingRain: WeatherType(
        weatherDesc = "Heavy freezing rain",
        iconRes = R.drawable.ic_snowyrainy,
        backgroundRes = R.drawable.snowy_sky
    )
    object SlightSnowFall: WeatherType(
        weatherDesc = "Slight snow fall",
        iconRes = R.drawable.ic_snowy,
        backgroundRes = R.drawable.snowy_sky
    )
    object ModerateSnowFall: WeatherType(
        weatherDesc = "Moderate snow fall",
        iconRes = R.drawable.ic_heavysnow,
        backgroundRes = R.drawable.snowy_sky
    )
    object HeavySnowFall: WeatherType(
        weatherDesc = "Heavy snow fall",
        iconRes = R.drawable.ic_heavysnow,
        backgroundRes = R.drawable.snowy_sky
    )
    object SnowGrains: WeatherType(
        weatherDesc = "Snow grains",
        iconRes = R.drawable.ic_heavysnow,
        backgroundRes = R.drawable.snowy_sky
    )
    object SlightRainShowers: WeatherType(
        weatherDesc = "Slight rain showers",
        iconRes = R.drawable.ic_rainshower,
        backgroundRes = R.drawable.rainy_sky
    )
    object ModerateRainShowers: WeatherType(
        weatherDesc = "Moderate rain showers",
        iconRes = R.drawable.ic_rainshower,
        backgroundRes = R.drawable.rainy_sky
    )
    object ViolentRainShowers: WeatherType(
        weatherDesc = "Violent rain showers",
        iconRes = R.drawable.ic_rainshower,
        backgroundRes = R.drawable.rainy_sky
    )
    object SlightSnowShowers: WeatherType(
        weatherDesc = "Light snow showers",
        iconRes = R.drawable.ic_snowy,
        backgroundRes = R.drawable.snowy_sky
    )
    object HeavySnowShowers: WeatherType(
        weatherDesc = "Heavy snow showers",
        iconRes = R.drawable.ic_snowy,
        backgroundRes = R.drawable.snowy_sky
    )
    object ModerateThunderstorm: WeatherType(
        weatherDesc = "Moderate thunderstorm",
        iconRes = R.drawable.ic_thunder,
        backgroundRes = R.drawable.rainy_sky
    )
    object SlightHailThunderstorm: WeatherType(
        weatherDesc = "Thunderstorm with slight hail",
        iconRes = R.drawable.ic_rainythunder,
        backgroundRes = R.drawable.rainy_sky
    )
    object HeavyHailThunderstorm: WeatherType(
        weatherDesc = "Thunderstorm with heavy hail",
        iconRes = R.drawable.ic_rainythunder,
        backgroundRes = R.drawable.rainy_sky
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when(code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}