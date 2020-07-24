package com.merttutsak.starter.utility.extension

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.*
import androidx.core.content.ContextCompat
import com.merttutsak.starter.R

/**
 * The extension brings the screen width. Return type is integer
 */
val Context?.screenWidth: Int
    get() {
        if (this.isNull()) return 0
        val windowManager = this!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.let {
            val dm = DisplayMetrics()
            it.defaultDisplay.getMetrics(dm)
            return dm.widthPixels
        }
    }

/**
 * The extension brings the screen height. Return type is integer
 */
val Context?.screenHeight: Int
    get() {
        if (this.isNull()) return 0
        val windowManager = this!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.let {
            val dm = DisplayMetrics()
            it.defaultDisplay.getMetrics(dm)
            return dm.heightPixels
        }
    }


fun Activity?.initStatusBar(): Int {
    this?.let {
        if (Build.VERSION.SDK_INT in 19..20) {
            it.setWindowFlag(true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            it.setWindowFlag(false)
            window.statusBarColor = ContextCompat.getColor(it, R.color.statusbar)
        }
    }

    return -1
}

fun Activity?.changeStatusBarColor(colorId: Int) {
    this?.let {
        if (Build.VERSION.SDK_INT >= 21) {
            window.statusBarColor = ContextCompat.getColor(it, colorId)
        }
    }
}

fun Activity?.setWindowFlag(on: Boolean) {
    this?.let {
        val win = it.window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        } else {
            winParams.flags =
                winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
        }
        win.attributes = winParams
    }
}

fun Activity?.setNavController(colorId: Int, isHide: Boolean, isDarkTheme: Boolean) {
    this?.window?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (isHide) {
                it.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            } else {
                if (Build.VERSION.SDK_INT >= 27 && !isDarkTheme) {
                    it.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                }
            }

            it.navigationBarColor = ContextCompat.getColor(it.context, colorId)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                it.navigationBarDividerColor = ContextCompat.getColor(it.context, colorId)
            }
        }
    }
}

fun Activity?.setNavController(typedValue: TypedValue, isHide: Boolean, isDarkTheme: Boolean) {
    this?.window?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (isHide) {
                it.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            } else {
                if (Build.VERSION.SDK_INT >= 27 && !isDarkTheme) {
                    it.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                }
            }

            it.navigationBarColor = ContextCompat.getColor(it.context, typedValue.resourceId)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                it.navigationBarDividerColor =
                    ContextCompat.getColor(it.context, typedValue.resourceId)
            }
        }
    }
}

fun Context.getNavigationbarHeight(): Int{
    val result = 0
    val hasMenuKey = ViewConfiguration.get(this).hasPermanentMenuKey()
    val hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)

    if (!hasMenuKey && !hasBackKey) { //The device has a navigation bar
        val orientation: Int = resources.configuration.orientation
        val resourceId: Int
        resourceId = if (isTablet()) {
            resources.getIdentifier(
                if (orientation == Configuration.ORIENTATION_PORTRAIT) "navigation_bar_height" else "navigation_bar_height_landscape",
                "dimen",
                "android"
            )
        } else {
            resources.getIdentifier(
                if (orientation == Configuration.ORIENTATION_PORTRAIT) "navigation_bar_height" else "navigation_bar_width",
                "dimen",
                "android"
            )
        }
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId)
        }
    }
    return result
}

fun Context.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId);
    }
    return result;
}