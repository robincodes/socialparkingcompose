package com.robinmaneiro.mapscomposeapp.presentation

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.robinmaneiro.mapscomposeapp.R
import com.robinmaneiro.mapscomposeapp.Screen

@Composable
fun SPSplashScreen(modifier: Modifier, navHostController: NavHostController) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val splashLottieAnimationRef = createRef()
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottiesplashanimation))
        LottieAnimation(
            composition = composition,
            modifier = modifier
                .constrainAs(splashLottieAnimationRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        iterations = LottieConstants.IterateForever
        )

        Handler(Looper.getMainLooper()).postDelayed({
            navHostController.navigate(Screen.LoginScreen.route)
        },1000)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSPSPlashScreen(){
    SPSplashScreen(modifier = Modifier, navHostController = rememberNavController())
}