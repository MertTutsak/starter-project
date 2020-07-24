package com.merttutsak.starter.utility.extension

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import com.merttutsak.starter.BuildConfig

val Context.versionOS: String
    get() {
        var vOS = Build.VERSION.RELEASE

        if (vOS.split('.').count() == 3) {
            return (vOS.split('.')[0] + "." + vOS.split('.')[1] + "." + vOS.split('.')[2])
        } else if (vOS.split('.').count() == 2) {
            return "$vOS.0"
        } else {
            return "$vOS.0.0"
        }
    }

val Context.versionApp: String
    get() {
        var vName = BuildConfig.VERSION_NAME
        if (vName.split('.').count() >= 3) {
            return (vName.split('.')[0] + "." + vName.split('.')[1] + "." + vName.split('.')[2])
        } else if (vName.split('.').count() == 2) {
            return "$vName.0"
        } else {
            return "$vName.0.0"
        }
    }

val Context.OS: String
    get() = "ANDROID"

fun Context.isTablet(): Boolean {
    return ((this.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE)
}
