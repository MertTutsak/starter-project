package com.merttutsak.starter.data.manager

import com.merttutsak.starter.utility.helper.analytics.AnalyticsHelper


interface DataManager {
    var analyticsHelper: AnalyticsHelper

    fun isUserLoggedIn():Boolean

    fun performUserLogout()
}