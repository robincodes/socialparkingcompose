package com.robinmaneiro.mapscomposeapp.presentation

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.robinmaneiro.mapscomposeapp.R

@Composable
fun SPReusablePermissionScreen(
    @RawRes lottieResId: Int,
    iterations: Int,
    permissionTitleText: String,
    permissionSubtitleText: String,
    permissionButtonEnableText: String,
    permissionButtonDisableText: String,
    onEnable: () -> Unit,
    onDisable: () -> Unit,
    modifier: Modifier,
) {
    ConstraintLayout(modifier.fillMaxSize()) {
        val (
            permissionLottieAnimation,
            permissionTitle,
            permissionSubtitle,
            permissionButtonEnable,
            permissionButtonDisable,
        ) = createRefs()

        ReusablePermissionAnimation(lottieResId = lottieResId,
            modifier.constrainAs(permissionLottieAnimation) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(permissionTitle.top)
            },
        iterations)


        Text(permissionTitleText,
            modifier.constrainAs(
                permissionTitle
            ) {
                linkTo(
                    top = parent.top,
                    bottom = parent.bottom,
                    end = parent.end,
                    start = parent.start,
                    verticalBias = 0.6f
                )
            },
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )

        Text(permissionSubtitleText,

            modifier
                .padding(horizontal = 20.dp)
                .constrainAs(permissionSubtitle) {
                    top.linkTo(permissionTitle.bottom, 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            textAlign = TextAlign.Center)

        Button(
            onClick = onEnable,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF008800),
                contentColor = Color.White
            ),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .constrainAs(permissionButtonEnable) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(permissionButtonDisable.top)
                }
        ) {
            Text(permissionButtonEnableText)
        }

        Button(
            onClick = onDisable,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF008800),
                contentColor = Color.White
            ),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .constrainAs(permissionButtonDisable) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, 20.dp)
                }
        ) {
            Text(permissionButtonDisableText)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSPPermissionsScreen() {
    SPReusablePermissionScreen(
        lottieResId = R.raw.lottielocationpermission,
        iterations = 1,
        permissionTitleText = "Location",
        permissionSubtitleText = "This will allow us to show you the area you are in along with the POIs close to you",
        permissionButtonEnableText = "Yes - Enable Location",
        permissionButtonDisableText = "No - Do Not Enable Location",
        onEnable = {},
        onDisable = {},
        Modifier)
}