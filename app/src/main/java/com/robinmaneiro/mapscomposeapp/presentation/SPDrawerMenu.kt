package com.robinmaneiro.mapscomposeapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.robinmaneiro.mapscomposeapp.Screen
import com.robinmaneiro.mapscomposeapp.util.navigateAndReplaceStartRoute
import com.robinmaneiro.mapscomposeapp.util.toDpValue


@Composable
fun SPDrawerMenu(topBarHeight: Int, navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(top = Dp(topBarHeight.toDpValue()))
            .fillMaxHeight(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top

    ) {


        TextButton(onClick = { navController.navigateAndReplaceStartRoute(Screen.LoginScreen.route) }) { Text("Sign in") }
        Divider(modifier = Modifier.padding(vertical = 2.dp), thickness = 0.4.dp, color = Color.DarkGray, startIndent = 10.dp)
        TextButton(onClick = { navController.navigate(Screen.RegistrationScreen.route) }) { Text("Sign up") }
        Divider(modifier = Modifier.padding(vertical = 2.dp), thickness = 0.4.dp, color = Color.DarkGray, startIndent = 10.dp)
        TextButton(onClick = { navController.navigate(Screen.SettingsScreen.route) }) { Text("Settings") }
//        Divider(modifier = Modifier.padding(vertical = 2.dp), thickness = 0.4.dp, color = Color.DarkGray, startIndent = 10.dp)
//        TextButton(onClick = { navController.navigate(Screen.LocationPermissionScreen.route) }) { Text("Location. Perm") }
//        Divider(modifier = Modifier.padding(vertical = 2.dp), thickness = 0.4.dp, color = Color.DarkGray, startIndent = 10.dp)
//        TextButton(onClick = { navController.navigate(Screen.CameraPermissionScreen.route) }) { Text("Camera. Perm.") }

//        Divider(modifier = Modifier.padding(vertical = 2.dp), thickness = 0.4.dp, color = Color.DarkGray, startIndent = 10.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSPDrawerMenu() {
    SPDrawerMenu(topBarHeight = 0, navController = rememberNavController())
}
