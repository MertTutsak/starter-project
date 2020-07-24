package com.merttutsak.starter.utility.extension

import android.view.Window
import android.view.WindowManager

fun Window?.fullBrightness(){
    this?.runCatching {
        addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        val params = attributes
        params.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL
        attributes = params
    }
}