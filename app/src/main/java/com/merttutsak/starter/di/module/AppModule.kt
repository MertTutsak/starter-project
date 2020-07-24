package com.merttutsak.starter.di.module

import android.content.Context
import com.merttutsak.starter.app.App
import com.merttutsak.starter.data.local.db.AppDatabase
import com.merttutsak.starter.data.local.preferences.AppPreferenceHelper
import com.merttutsak.starter.data.local.preferences.PreferenceHelper
import com.merttutsak.starter.data.manager.AppDataManager
import com.merttutsak.starter.data.manager.DataManager
import com.merttutsak.starter.utility.Constants
import com.merttutsak.starter.utility.helper.analytics.AnalyticsHelper
import com.merttutsak.starter.utility.helper.analytics.FirebaseAnalyticsHelper
import com.merttutsak.starter.utility.helper.ThemeHelper
import com.merttutsak.starter.utility.provider.AppLanguageProvider
import com.merttutsak.starter.utility.provider.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module(includes = [AppModuleBinds::class])
class AppModule {

    @Provides
    fun provideContext(application: App): Context = application.applicationContext

    @Provides
    @Singleton
    fun providesDataManager(dataManager: AppDataManager): DataManager = dataManager

    @Provides
    @Singleton
    fun providesDatabase(context: Context): AppDatabase = AppDatabase.createDatabase(context)

    @Provides
    @Singleton
    internal fun provideSharedPref(
        context: Context
    ): PreferenceHelper = AppPreferenceHelper(context, Constants.App.PREF_NAME)

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider

    @Provides
    @Singleton
    fun providesAppLanguage(context: Context): AppLanguageProvider {
        return AppLanguageProvider(context)
    }

    @Singleton
    @Provides
    fun providesAnalyticsHelper(
        context: Context
    ): AnalyticsHelper = FirebaseAnalyticsHelper(context)

    @Provides
    fun providesDarkModeHelper(
        context: Context
    ): ThemeHelper = ThemeHelper(provideSharedPref(context))

}