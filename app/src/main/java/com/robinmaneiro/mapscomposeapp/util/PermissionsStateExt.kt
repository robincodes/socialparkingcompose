package com.robinmaneiro.mapscomposeapp.util

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@OptIn(ExperimentalPermissionsApi::class)
fun PermissionState.isPermanentlyDeclined(): Boolean {
    return !hasPermission && !shouldShowRationale
}