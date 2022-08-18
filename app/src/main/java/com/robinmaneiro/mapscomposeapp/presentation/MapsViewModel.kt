package com.robinmaneiro.mapscomposeapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.MapStyleOptions
import com.robinmaneiro.mapscomposeapp.domain.model.ParkingSpot
import com.robinmaneiro.mapscomposeapp.domain.repository.ParkingSpotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val repository: ParkingSpotRepository
) : ViewModel() {

    var state by mutableStateOf(MapState())

    init {
        viewModelScope.launch {
            repository.getParkingSpot().collectLatest { spots ->
                state = state.copy(
                    parkingSpots = spots
                )
            }
        }
    }

    fun onEvent(event: MapEvent) {
        when (event) {
            is MapEvent.ToggleUberMapEnabled -> {
                state = state.copy(
                    properties = state.properties.copy(
                        mapStyleOptions = if(state.isUberStyleMap) {
                            MapStyleOptions(MapStyleUber.json)
                        } else null,
                    ),
                    isUberStyleMap = !state.isUberStyleMap
                )
            }
            is MapEvent.ToggleBuildingsEnabled -> state = state.copy(properties = state.properties.copy(isBuildingEnabled = !state.properties.isBuildingEnabled))
            is MapEvent.ToggleTrafficEnabled -> state = state.copy(properties = state.properties.copy(isTrafficEnabled = !state.properties.isTrafficEnabled))
            is MapEvent.ToggleShowLocationEnabled -> state = state.copy(properties = state.properties.copy(isMyLocationEnabled = !state.properties.isMyLocationEnabled))
            is MapEvent.OnMapLongClick -> {
                viewModelScope.launch {
                    repository.insertParkingSpot(
                        ParkingSpot(
                            lat = event.latLng.latitude,
                            lng = event.latLng.longitude
                        )
                    )
                }
            }
            is MapEvent.OnInfoWindowLongClick -> {
                viewModelScope.launch {
                    repository.deleteParkingSpot(event.spot)
                }
            }
            is MapEvent.OnBottomSheetExpanded -> {
                state = state.copy(parkingSpot = event.spot)
            }
        }
    }
}