package com.merttutsak.starter.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

import com.merttutsak.starter.utility.Constants
import com.merttutsak.starter.utility.provider.AppLanguageProvider
import javax.inject.Inject


class AppPreferenceHelper @Inject constructor(
    context: Context,
    private val prefFileName: String
) : PreferenceHelper {

    companion object {
        const val PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE"
        const val PREF_KEY_CURRENT_LANGUAGE = "PREF_KEY_CURRENT_LANGUAGE"
        const val PREF_KEY_CURRENT_THEME = "PREF_KEY_CURRENT_THEME"
    }

    private val mPrefs: SharedPreferences =
        context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override fun setAppLanguage(language: String?) =
        mPrefs.edit { putString(PREF_KEY_CURRENT_LANGUAGE, language) }

    override fun getAppLanguage(): String? =
        mPrefs.getString(PREF_KEY_CURRENT_LANGUAGE, AppLanguageProvider.DEFAULT_LANG.code())

    override fun getAppTheme(): Int = mPrefs.getInt(PREF_KEY_CURRENT_THEME, 0)

    override fun setAppTheme(themeId: Int) =
        mPrefs.edit { putInt(PREF_KEY_CURRENT_THEME, themeId) }

    override fun getUserLoggedInMode() = mPrefs.getInt(
        PREF_KEY_USER_LOGGED_IN_MODE,
        Constants.App.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.ordinal
    )

    override fun setUserLoggedInMode(mode: Constants.App.LoggedInMode) {
        mPrefs.edit { putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.ordinal) }
    }

}