package com.robinmaneiro.mapscomposeapp

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.robinmaneiro.mapscomposeapp.ui.theme.MapsComposeApp
import com.robinmaneiro.mapscomposeapp.util.isPermanentlyDeclined

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapsComposeApp {
                val permissionState = rememberMultiplePermissionsState(
                    permissions = listOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.CAMERA
                    )
                )
                val context = LocalContext.current
                val lifecycleOwner = LocalLifecycleOwner.current
//                lifecycleOwner.lifecycle.addObserver() -> this would be a mistake as we cannot control how often this piece of code is called

                DisposableEffect(key1 = lifecycleOwner //whenever the lifecycleOwner actually changes gere it wil trigger the effect
                    , effect = {
                        val observer = LifecycleEventObserver { _, event ->
                            if (event == Lifecycle.Event.ON_RESUME) {
                                permissionState.launchMultiplePermissionRequest()
                            }
                        }
                        lifecycleOwner.lifecycle.addObserver(observer)


                        //We need to finish every of these DisposableEffect handlers with an 'onDispose' function
                        //when the DisposableEffect handler is cleared up this onDispose function will be called
                        onDispose {
                            lifecycleOwner.lifecycle.removeObserver(observer)
                        }
                    })


//                 A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        permissionState.permissions.forEach { perm ->
                            when (perm.permission) {
                                Manifest.permission.ACCESS_FINE_LOCATION -> when {
                                    perm.hasPermission -> Text(text = "Location permission accepted")
                                    perm.shouldShowRationale -> Text(text = "Location permission is used to give you directions to the parking spot ")
                                    perm.isPermanentlyDeclined() -> Text(text = "Location permission was declined - please enable it in the app settings")
                                }
                                Manifest.permission.CAMERA -> when {
                                    perm.hasPermission -> Text(text = "Camera permission accepted")
                                    perm.shouldShowRationale -> Text(text = "Camera location is used to take a picture of the parking spot")
                                    perm.isPermanentlyDeclined() -> Text(text = "Camera permission was declined - please enable it in the app settings")
                                }
                            }
                        }
                        TextButton(onClick = { startActivity(Intent(context, MapActivity::class.java)) }) {
                            Text(text = "Go to Map Activity")
                        }

//                        SPReusablePermissionScreen(
//                            lottieResId = R.raw.lottielocationpermission,
//                            permissionTitleText = "Location",
//                            permissionSubtitleText = "This will allow us to show you the area you are in along with the POIs close to you"
//                            , permissionButtonEnableText = "Yes - Enable Location",
//                            permissionButtonDisableText = "No - Do Not Enable Location",
//                            Modifier)


                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MapsComposeApp {
        Greeting("Android")
    }
}