package com.merttutsak.starter.data.local.preferences

import android.content.SharedPreferences
import androidx.core.content.edit

import com.merttutsak.starter.utility.Constants
import com.merttutsak.starter.utility.provider.AppLanguageProvider
import javax.inject.Inject


class AppSharedPrefHelper @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SharedPrefHelper {

    internal enum class SHARED_PREF_KEY {
        USER_LOGGED_IN_MODE,
        CURRENT_LANGUAGE,
        CURRENT_THEME;
    }

    override fun setAppLanguage(language: String?) =
        sharedPreferences.edit { putString(SHARED_PREF_KEY.CURRENT_LANGUAGE.name, language) }

    override fun getAppLanguage(): String? =
        sharedPreferences.getString(
            SHARED_PREF_KEY.CURRENT_LANGUAGE.name,
            AppLanguageProvider.DEFAULT_LANG.code()
        )

    override fun getAppTheme(): Int =
        sharedPreferences.getInt(SHARED_PREF_KEY.CURRENT_THEME.name, 0)

    override fun setAppTheme(themeId: Int) =
        sharedPreferences.edit { putInt(SHARED_PREF_KEY.CURRENT_THEME.name, themeId) }

    override fun getUserLoggedInMode() = sharedPreferences.getInt(
        SHARED_PREF_KEY.USER_LOGGED_IN_MODE.name,
        Constants.App.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.ordinal
    )

    override fun setUserLoggedInMode(mode: Constants.App.LoggedInMode) {
        sharedPreferences.edit { putInt(SHARED_PREF_KEY.USER_LOGGED_IN_MODE.name, mode.ordinal) }
    }

}