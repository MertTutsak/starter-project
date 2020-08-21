package com.merttutsak.starter.di.module

import android.content.Context
import com.merttutsak.starter.app.App
import com.merttutsak.starter.data.local.preferences.SharedPrefHelper
import com.merttutsak.starter.data.manager.AppDataManager
import com.merttutsak.starter.data.manager.DataManager
import com.merttutsak.starter.utility.helper.analytics.AnalyticsHelper
import com.merttutsak.starter.utility.helper.ThemeHelper
import com.merttutsak.starter.utility.helper.analytics.FirebaseAnalyticsHelper
import com.merttutsak.starter.utility.provider.AppLanguageProvider
import com.merttutsak.starter.utility.provider.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Singleton

@Module(includes = [AppModuleBinds::class])
class AppModule {

    @Provides
    fun provideContext(application: App): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideDataManager(dataManager: AppDataManager): DataManager = dataManager

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider

    @Provides
    @Singleton
    fun provideAppLanguage(sharedPreferencesHelper: SharedPrefHelper): AppLanguageProvider {
        return AppLanguageProvider(sharedPreferencesHelper)
    }

    @Singleton
    @Provides
    fun provideAnalytics(
        context: Context
    ): AnalyticsHelper = FirebaseAnalyticsHelper(context)

    @Provides
    fun provideTheme(sharedPrefHelper: SharedPrefHelper): ThemeHelper =
        ThemeHelper(sharedPrefHelper)

}