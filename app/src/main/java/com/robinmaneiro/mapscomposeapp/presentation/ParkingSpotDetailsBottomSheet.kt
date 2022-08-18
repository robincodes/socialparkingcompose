package com.robinmaneiro.mapscomposeapp.presentation

import android.location.Geocoder
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.robinmaneiro.mapscomposeapp.domain.model.ParkingSpot
import com.robinmaneiro.mapscomposeapp.util.orZero
import java.util.*

@Composable
fun ParkingSpotDetailsBottomSheet( spot: ParkingSpot?) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(300.dp)

    ) {
        val geocoder = Geocoder(LocalContext.current, Locale.getDefault())
        val addresses = geocoder.getFromLocation(spot?.lat.orZero(),spot?.lng.orZero(),1)

        Spacer(modifier = Modifier
            .size(width = 30.dp, height = 4.dp)
            .background(Color.Black, RoundedCornerShape(2.dp))
            .align(Alignment.CenterHorizontally))
        //Text(text = "username is: ${spot?.userName}")
        Text(text = "latitude: ${"%.2f".format(spot?.lat)}")
        Text(text = "longitude: ${"%.2f".format(spot?.lng)}")
        if(addresses.isNotEmpty()) {
            Text(text = "Address: ${addresses[0].getAddressLine(0)}")
            Text(text = "State: ${addresses[0].adminArea}")
            Text(text = "Country: ${addresses[0].countryName}")
            Text(text = "Postal Code: ${addresses[0].postalCode}")
            Text(text = "Known Name: ${addresses[0].featureName}")

        }
    }
}