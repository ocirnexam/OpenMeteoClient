package com.ricotronics.openmeteoweatherclient.di

import com.ricotronics.openmeteoweatherclient.data.location.DefaultLocationTracker
import com.ricotronics.openmeteoweatherclient.data.repository.WeatherRepositoryImpl
import com.ricotronics.openmeteoweatherclient.domain.location.LocationTracker
import com.ricotronics.openmeteoweatherclient.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository
}