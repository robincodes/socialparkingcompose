package com.robinmaneiro.mapscomposeapp.presentation

import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.robinmaneiro.mapscomposeapp.util.drawerShape

@Composable

fun SPScaffoldScreen(navController: NavHostController, viewModel: MapsViewModel) {
    val scaffoldState = rememberScaffoldState(
        rememberDrawerState(initialValue = DrawerValue.Closed)
    )
    var bottomBarHeight by remember { mutableStateOf(0) } //Delegation save us from writing '.value' each time
    var topBarHeight by remember { mutableStateOf(0) }

    Scaffold(
        scaffoldState = scaffoldState,
        content = {
            MapScreen(bottomBarHeight, viewModel)
        },
        // If just set to true it won't allow the map to be dragged - this is not solved yet in compose-ui 1.1.1
        // https://stackoverflow.com/questions/68560614/jetpack-compose-scaffold-drawer-opens-when-dragging-mapview
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        topBar = { SPTopAppBar(scaffoldState) { topBarHeight = it } },
        bottomBar = { SPBottomAppBar { bottomBarHeight = it } },
        drawerContent = { SPDrawerMenu(topBarHeight, navController) },
        drawerElevation = 5.dp,
        drawerShape = drawerShape(topBarHeight, bottomBarHeight),
//                        drawerScrimColor = Color.Green.copy(alpha = 0.3f)
    )

}