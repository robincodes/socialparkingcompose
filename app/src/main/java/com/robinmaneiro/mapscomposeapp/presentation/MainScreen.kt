package com.robinmaneiro.mapscomposeapp.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieConstants
import com.robinmaneiro.mapscomposeapp.R
import com.robinmaneiro.mapscomposeapp.Screen

@Composable
fun MainScreen(viewModel: MapsViewModel) {
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) { SPSplashScreen(modifier = Modifier, navHostController = navHostController) }
        composable(Screen.ScaffoldScreen.route) { SPScaffoldScreen(navHostController, viewModel) }
        composable(Screen.LoginScreen.route) { SPLoginScreen(navHostController) }
        composable(Screen.RegistrationScreen.route) { SPRegistrationScreen(navHostController) }
        composable(Screen.SettingsScreen.route) { SPSettingsScreen(viewModel, Modifier) }
        composable(Screen.LocationPermissionScreen.route) {
            SPReusablePermissionScreen(
                lottieResId = R.raw.lottielocationpermission,
                1,
                permissionTitleText = "Location",
                permissionSubtitleText = "This will allow us to show you the area you are in along with the POIs close to you", permissionButtonEnableText = "Yes - Enable Location",
                permissionButtonDisableText = "No - Do Not Enable Location",
                onEnable = { navHostController.navigate(Screen.CameraPermissionScreen.route) },
                onDisable = { navHostController.navigate(Screen.CameraPermissionScreen.route) },
                Modifier)
            BackHandler(true) {  }

        }
        composable(Screen.CameraPermissionScreen.route) {
            SPReusablePermissionScreen(
                lottieResId = R.raw.lottiecamerapermission,
                LottieConstants.IterateForever,
                permissionTitleText = "Camera",
                permissionSubtitleText = "This will allow you to personalise your profile", permissionButtonEnableText = "Yes - Enable Camera",
                permissionButtonDisableText = "No - Do Not Enable Camera",
                onEnable = { navHostController.navigate(Screen.ScaffoldScreen.route) },
                onDisable = { navHostController.navigate(Screen.ScaffoldScreen.route) },
                Modifier)
            BackHandler(true) {  }
        }
    }
}