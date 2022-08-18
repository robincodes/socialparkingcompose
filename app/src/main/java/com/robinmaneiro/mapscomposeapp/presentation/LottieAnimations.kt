package com.robinmaneiro.mapscomposeapp.presentation

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun ReusablePermissionAnimation(@RawRes lottieResId: Int, modifier: Modifier, iterations: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieResId))
    LottieAnimation(
        composition = composition,
        modifier = modifier.size(300.dp),
        iterations = iterations
    )
}