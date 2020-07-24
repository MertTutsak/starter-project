package com.merttutsak.starter.di.module

import android.content.Context
import android.content.SharedPreferences
import com.merttutsak.starter.data.local.db.AppDatabase
import com.merttutsak.starter.data.local.preferences.AppSharedPrefHelper
import com.merttutsak.starter.data.local.preferences.SharedPrefHelper
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
        sharedPreferences: SharedPreferences
    ): SharedPrefHelper = AppSharedPrefHelper(sharedPreferences)

    @Provides
    @Singleton
    internal fun provideSharedPreferences(
        context: Context
    ): SharedPreferences =
        context.getSharedPreferences(Constants.App.PREF_NAME, Context.MODE_PRIVATE)

}