package com.robinmaneiro.mapscomposeapp.presentation

import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties
import com.robinmaneiro.mapscomposeapp.domain.model.ParkingSpot

data class MapState(
    val parkingSpots: List<ParkingSpot> = emptyList(),
    val parkingSpot: ParkingSpot? = null,
    var properties: MapProperties = MapProperties(
        isBuildingEnabled = true,
        isTrafficEnabled = true,
        isMyLocationEnabled = true
    ),
    var mapStyleOptions: MapStyleOptions? = MapStyleOptions(MapStyleUber.json),
    val isUberStyleMap: Boolean = true,
    )