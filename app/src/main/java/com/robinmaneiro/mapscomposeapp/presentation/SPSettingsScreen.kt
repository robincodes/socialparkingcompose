package com.robinmaneiro.mapscomposeapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.robinmaneiro.mapscomposeapp.ui.composables.ReusableRoundedTextSwitch

@Composable
fun SPSettingsScreen(viewModel: MapsViewModel, modifier: Modifier) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()).
                padding(horizontal = 20.dp, vertical = 10.dp)
    ) {

        Text(textAlign = TextAlign.Center, text = "SETTINGS")
        Spacer(modifier = modifier.height(10.dp))
        Text(textAlign = TextAlign.Start, text = "Map Settings")

        ReusableRoundedTextSwitch(viewModel, text = "Uber-style map", event = MapEvent.ToggleUberMapEnabled, checked = viewModel.state.properties.mapStyleOptions != null)
        ReusableRoundedTextSwitch(viewModel, text = "Show buildings", event = MapEvent.ToggleBuildingsEnabled, viewModel.state.properties.isBuildingEnabled)
        ReusableRoundedTextSwitch(viewModel, text = "Show traffic ", event = MapEvent.ToggleTrafficEnabled, viewModel.state.properties.isTrafficEnabled)
        ReusableRoundedTextSwitch(viewModel, text = "Show location on map", event = MapEvent.ToggleShowLocationEnabled, viewModel.state.properties.isMyLocationEnabled)

        Spacer(modifier = modifier.height(30.dp))
        Text(textAlign = TextAlign.Start, text = "App Permissions")
        ReusableRoundedTextSwitch(viewModel, text = "Location Permission", event = MapEvent.ToggleUberMapEnabled, checked = viewModel.state.properties.mapStyleOptions != null)
        ReusableRoundedTextSwitch(viewModel, text = "Camera Permission", event = MapEvent.ToggleBuildingsEnabled, viewModel.state.properties.isBuildingEnabled)

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSPSettingsScreen() {
    SPSettingsScreen(viewModel = viewModel(), modifier = Modifier)
}
