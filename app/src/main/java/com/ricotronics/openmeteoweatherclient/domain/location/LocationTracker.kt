package com.ricotronics.openmeteoweatherclient.domain.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}