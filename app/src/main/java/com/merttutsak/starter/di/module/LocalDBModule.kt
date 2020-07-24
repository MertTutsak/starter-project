package com.merttutsak.starter.di.module

import android.content.Context
import com.merttutsak.starter.data.local.db.AppDatabase
import com.merttutsak.starter.data.local.preferences.AppPreferenceHelper
import com.merttutsak.starter.data.local.preferences.PreferenceHelper
import com.merttutsak.starter.utility.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDBModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase = AppDatabase.createDatabase(context)

    @Provides
    @Singleton
    internal fun provideSharedPref(
        context: Context
    ): PreferenceHelper = AppPreferenceHelper(context, Constants.App.PREF_NAME)

}