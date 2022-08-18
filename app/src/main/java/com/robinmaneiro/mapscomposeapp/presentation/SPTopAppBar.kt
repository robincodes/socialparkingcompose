package com.robinmaneiro.mapscomposeapp.presentation

import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch


@Composable
fun SPTopAppBar(scaffoldState: ScaffoldState, heightCallback: (Int) -> Unit) {
    val coroutineScope = rememberCoroutineScope()

    TopAppBar(title = { Text("Maps Compose App") },
        modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
            heightCallback(layoutCoordinates.size.height) //pass the coordinates as a callback to calculate the height of the draw
        },
        backgroundColor = Color(0xFF008800),
        navigationIcon = { //TODO: Solve this transition to make smooth it
            IconToggleButton(checked = scaffoldState.drawerState.isOpen, onCheckedChange = { }) {
                Icon(
                    if (!scaffoldState.drawerState.isOpen) Icons.Default.Menu else Icons.Default.Close,
                    "Drawer Menu Icon",
                    modifier = Modifier.clickable(onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    })
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSPTopAppBar() {
    var topBarHeight by remember { mutableStateOf(0) }

    SPTopAppBar(scaffoldState = rememberScaffoldState(), heightCallback = { topBarHeight = it })
}
