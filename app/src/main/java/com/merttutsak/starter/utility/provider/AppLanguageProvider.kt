package com.merttutsak.starter.utility.provider

import android.content.Context
import com.merttutsak.starter.data.local.preferences.AppPreferenceHelper
import com.merttutsak.starter.ui.common.base.view.activity.BaseActivity
import java.io.Serializable
import javax.inject.Inject

class AppLanguageProvider @Inject internal constructor(val context: Context) {
    private var appLanguage: LanguageType? = null

    fun getAppLanguage(): LanguageType {
        var currentLanguage = AppPreferenceHelper(
            context,
            AppPreferenceHelper.PREF_KEY_CURRENT_LANGUAGE
        ).getAppLanguage()

        appLanguage = if (currentLanguage.isNullOrEmpty()) {
            DEFAULT_LANG
        } else {
            when (currentLanguage) {
                LanguageType.TURKISH.code() -> LanguageType.TURKISH
                LanguageType.ENGLISH.code() -> LanguageType.ENGLISH
                else -> DEFAULT_LANG
            }
        }

        return appLanguage!!
    }

    fun setLanguage(activity: BaseActivity<*, *>, languageType: LanguageType) {
        AppPreferenceHelper(
            activity,
            AppPreferenceHelper.PREF_KEY_CURRENT_LANGUAGE
        ).setAppLanguage(languageType.code())
        activity.recreate()
        BusProvider.getInstance().post(LanguageChangeEvent())
    }

    fun checkLanguageSupport(language: LanguageType): Boolean {
        return availableLanguages.contains(language)
    }

    companion object {
        val DEFAULT_LANG = LanguageType.TURKISH

        val availableLanguages: ArrayList<LanguageType>
            get() {
                val languages: ArrayList<LanguageType> = ArrayList()
                languages.add(LanguageType.ENGLISH)
                languages.add(LanguageType.TURKISH)
                return languages
            }
    }

    enum class LanguageType() {
        TURKISH {
            override fun value() = "Türkçe"
            override fun code() = "tr"
        },
        ENGLISH {
            override fun value() = "English"
            override fun code() = "en"
        };

        abstract fun value(): String
        abstract fun code(): String
    }

}

class LanguageChangeEvent() : Serializable
