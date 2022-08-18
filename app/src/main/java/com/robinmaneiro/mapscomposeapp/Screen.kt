package com.robinmaneiro.mapscomposeapp

sealed class Screen(val route: String) {
    object SplashScreen: Screen(route = "SplashScreen")
    object LoginScreen : Screen(route = "SignIn")
    object RegistrationScreen : Screen(route = "SignUp")
    object LocationPermissionScreen: Screen(route = "LocationPermission")
    object CameraPermissionScreen: Screen(route = "CameraPermission")
    object ScaffoldScreen: Screen(route = "Scaffold")
    object Map : Screen(route = "Map")
    object SettingsScreen : Screen(route = "Settings")
}