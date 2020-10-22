package com.orion.sprinttracker.utils

import android.Manifest.permission.*
import android.content.Context
import android.os.Build
import pub.devrel.easypermissions.EasyPermissions
import java.util.jar.Manifest

class MainUtility {
    companion object{
        /**
         * Checks if the user accepted the necessary location permissions or not
         */
        fun hasLocationPermissions(context: Context): Boolean {
            return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                EasyPermissions.hasPermissions(
                    context,
                    ACCESS_FINE_LOCATION,
                    ACCESS_COARSE_LOCATION
                )
            } else {
                EasyPermissions.hasPermissions(
                    context,
                    ACCESS_FINE_LOCATION,
                    ACCESS_COARSE_LOCATION,
                    ACCESS_BACKGROUND_LOCATION
                )
            }
        }
    }
}