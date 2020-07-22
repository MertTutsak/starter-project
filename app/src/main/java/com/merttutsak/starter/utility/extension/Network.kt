package com.merttutsak.starter.utility.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build

fun Context.isNetworkConnection(): Boolean {
    val conMgr = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        return (conMgr!!.activeNetwork != null)
    } else {
        return conMgr?.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)?.state == NetworkInfo.State.CONNECTED ||
                conMgr?.getNetworkInfo(ConnectivityManager.TYPE_WIFI)?.state == NetworkInfo.State.CONNECTED
    }
}