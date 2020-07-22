package com.merttutsak.starter.data.local.preferences

import com.merttutsak.starter.utility.Constants


interface PreferenceHelper {

    fun getAppTheme(): Int

    fun setAppTheme(themeId: Int)

    fun getUserLoggedInMode(): Int

    fun setUserLoggedInMode(mode: Constants.App.LoggedInMode)

    fun setAppLanguage(language: String?)

    fun getAppLanguage(): String?

}