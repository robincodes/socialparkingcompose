package com.robinmaneiro.mapscomposeapp.presentation

import androidx.compose.material.BottomAppBar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned

@Composable
fun SPBottomAppBar(heightCallback: (Int)-> Unit) {
    BottomAppBar(modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
        heightCallback(layoutCoordinates.size.height) //pass the coordinates as a callback to calculate the height of the draw
    }, backgroundColor = Color(0xFF008800)) {
        Text("Bottom App Bar")
    }
}
