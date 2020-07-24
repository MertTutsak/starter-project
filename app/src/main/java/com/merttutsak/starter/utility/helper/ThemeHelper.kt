package com.merttutsak.starter.utility.helper

import android.content.Context
import android.util.TypedValue
import androidx.annotation.StyleRes
import com.merttutsak.starter.data.local.preferences.SharedPrefHelper
import com.merttutsak.starter.ui.common.base.view.activity.BaseActivity
import java.io.Serializable
import javax.inject.Inject

class ThemeHelper @Inject constructor(val sharedPrefHelper: SharedPrefHelper) {

    companion object {
        fun getTypeValue(context: Context, attrId: Int): TypedValue {
            val typedValue = TypedValue()
            context.theme.resolveAttribute(attrId, typedValue, true)
            return typedValue
        }
    }

    fun getModeValue(): Int {
        val value = sharedPrefHelper.getAppTheme()
        if (value == 0) {
            return AppCompatDelegate.MODE_NIGHT_NO
        }
        return value
    }

    @StyleRes
    fun getThemeValue():Int = if(getModeValue() == AppCompatDelegate.MODE_NIGHT_YES) R.style.AppTheme_Base_Night else R.style.AppTheme_Base_Light

    fun init(activity: BaseActivity<*,*>) {
        if (getModeValue() == AppCompatDelegate.MODE_NIGHT_YES) {
            setDarkTheme(activity,true)
        } else {
            setLightTheme(activity,true)
        }
    }

    fun setDarkTheme(activity: BaseActivity<*,*>, isInit: Boolean = false) {
        if(AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_YES){
            sharedPrefHelper.setAppTheme(AppCompatDelegate.MODE_NIGHT_YES)
            activity.setTheme(R.style.AppTheme_Base_Night)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            isInit.isFalse {
                activity.recreate()
                BusProvider.getInstance().post(ThemeChangeEvent(isDarkTheme()))
            }
        }
    }

    fun setLightTheme(activity: BaseActivity<*,*>, isInit: Boolean = false) {
        if(AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_NO){
            sharedPrefHelper.setAppTheme(AppCompatDelegate.MODE_NIGHT_NO)
            activity.setTheme(R.style.AppTheme_Base_Light)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            isInit.isFalse {
                activity.recreate()
                BusProvider.getInstance().post(ThemeChangeEvent(isDarkTheme()))
            }
        }
    }

    fun isDarkTheme(): Boolean = sharedPrefHelper.getAppTheme() == AppCompatDelegate.MODE_NIGHT_YES

}

class ThemeChangeEvent(isDarkMode: Boolean) : Serializable