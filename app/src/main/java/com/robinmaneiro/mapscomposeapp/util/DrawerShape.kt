package com.robinmaneiro.mapscomposeapp.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection


@Composable
fun drawerShape(topBarHeight: Int, bottomBarHeight: Int) = object : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        return Outline.Rounded(RoundRect(
            left = 0f,
            top = topBarHeight.toFloat(),
            right = 400f,
            bottom = size.height - bottomBarHeight,
            topRightCornerRadius = CornerRadius(10.toPxValue(), 10.toPxValue())
        ))
    }
}