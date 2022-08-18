package com.robinmaneiro.mapscomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.robinmaneiro.mapscomposeapp.presentation.MainScreen
import com.robinmaneiro.mapscomposeapp.ui.theme.MapsComposeApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapsComposeApp {
                MainScreen(viewModel())
            }
        }
    }
}