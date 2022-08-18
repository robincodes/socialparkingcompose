package com.robinmaneiro.mapscomposeapp.presentation

import com.google.android.gms.maps.model.LatLng
import com.robinmaneiro.mapscomposeapp.domain.model.ParkingSpot

//it will reflect ui events - things the user can do in our screen
sealed class MapEvent {
    object ToggleUberMapEnabled : MapEvent()
    object ToggleBuildingsEnabled : MapEvent()
    object ToggleTrafficEnabled : MapEvent()
    object ToggleShowLocationEnabled : MapEvent()
    data class OnMapLongClick(val latLng: LatLng): MapEvent()
    data class OnInfoWindowLongClick(val spot: ParkingSpot): MapEvent()
    data class OnBottomSheetExpanded(val spot: ParkingSpot): MapEvent()
}