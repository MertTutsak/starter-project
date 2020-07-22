package com.merttutsak.starter.utility.extension

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import androidx.core.app.ActivityCompat
import com.merttutsak.starter.BuildConfig
import android.content.res.AssetManager


val Context.versionOS: String
    get() = Build.VERSION.RELEASE

val Context.versionApp: String
    get() = BuildConfig.VERSION_NAME

val Context.OS: String
    get() = "android"

fun Context.isTablet(): Boolean {
    return ((this.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE)
}