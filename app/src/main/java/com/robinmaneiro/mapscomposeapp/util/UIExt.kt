package com.robinmaneiro.mapscomposeapp.util

import android.content.res.Resources

fun Int.toDpValue() = this / Resources.getSystem().displayMetrics.density
fun Int.toPxValue() = this * Resources.getSystem().displayMetrics.density