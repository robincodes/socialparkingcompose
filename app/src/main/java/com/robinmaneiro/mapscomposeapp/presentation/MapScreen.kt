package com.robinmaneiro.mapscomposeapp.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.robinmaneiro.mapscomposeapp.domain.model.ParkingSpot
import com.robinmaneiro.mapscomposeapp.util.toDpValue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
public fun MapScreen(
    bottomBarHeight: Int,
    viewModel: MapsViewModel
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    val coroutineScope = rememberCoroutineScope()

    val uiSettings = remember { //by using remember we make sure we don't recreate this object in every recomposition
        MapUiSettings(zoomControlsEnabled = false)
    }

    var parkingSpot: ParkingSpot? = null

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
//        floatingActionButton = {
//            FloatingActionButton(onClick = { viewModel.onEvent(MapEvent.ToggleUberMap) }) {
//                Icon(
//                    imageVector = if (viewModel.state.isUberMap) Icons.Default.ToggleOff else Icons.Default.ToggleOn, contentDescription = "Toggle Uber Map"
//                )
//            }
//        },
        sheetShape = RoundedCornerShape(10.dp, 10.dp),
        sheetBackgroundColor = Color.White.copy(alpha = 0.9f), sheetContent = {
            ParkingSpotDetailsBottomSheet(viewModel.state.parkingSpot)
        }, sheetPeekHeight = Dp(bottomBarHeight.toDpValue())
    ) {

        GoogleMap(modifier = Modifier.fillMaxSize(), properties = viewModel.state.properties
        , uiSettings = uiSettings, cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(LatLng(53.79900883537328, -1.5464984637913353), 10f) //TODO: Set camera position to location
        }, onMapLongClick = {
            if (bottomSheetScaffoldState.bottomSheetState.isExpanded) {
                coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.collapse() }
            }
            viewModel.onEvent(MapEvent.OnMapLongClick(it))
        }, onMapClick = {
            if (bottomSheetScaffoldState.bottomSheetState.isExpanded) {
                coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.collapse() }
            }
        }) {
            viewModel.state.parkingSpots.forEach { spot ->
                Marker(
                    position = LatLng(spot.lat, spot.lng),
                    title = "Parking spot (${"%.2f".format(spot.lat)}, ${"%.2f".format(spot.lng)})",
                    snippet = "Tap to show more details",
                    rotation = 30f,
                    onInfoWindowClick = {
                        coroutineScope.launch {
                            with(bottomSheetScaffoldState.bottomSheetState) {
                                parkingSpot = spot
//                                if (isExpanded) collapse() else expand()
                                viewModel.onEvent(MapEvent.OnBottomSheetExpanded(spot))
                                expand()
                            }
                        }


//                        viewModel.onEvent(
//                            MapEvent.OnInfoWindowLongClick(spot)
//                                    coroutineScope.launch { bottomSheetScaffoldState.bottomSheetState.expand() }
//                        )
                    },
                    draggable = true,
                    onClick = {
                        it.showInfoWindow()
                        true // We handled the click event
                    }, icon = BitmapDescriptorFactory.defaultMarker( //Here we can create our own market

                    )
                )
            }
        }
    }
}