package com.merttutsak.starter.di.module

import android.content.Context
import com.merttutsak.starter.app.App
import com.merttutsak.starter.BuildConfig
import com.merttutsak.starter.data.local.db.AppDatabase
import com.merttutsak.starter.data.local.preferences.AppPreferenceHelper
import com.merttutsak.starter.data.local.preferences.PreferenceHelper
import com.merttutsak.starter.di.ApiKeyInfo
import com.merttutsak.starter.di.PreferenceInfo
import com.merttutsak.starter.data.remote.service.api.ApiHelper
import com.merttutsak.starter.data.remote.service.api.ApiHelperImp
import com.merttutsak.starter.data.manager.DataManagerImp
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
    @ApiKeyInfo
    internal fun provideApiKey(): String = BuildConfig.X_APP_KEY

    @Provides
    @PreferenceInfo
    internal fun provideprefFileName(): String = Constants.App.PREF_NAME

    @Provides
    @Singleton
    internal fun providePrefHelper(appPreferenceHelper: AppPreferenceHelper): PreferenceHelper =
        appPreferenceHelper

    @Provides
    @Singleton
    fun providesRemoteService(context: Context): ApiHelper {
        return ApiHelper.createRetrofit(context)
    }

    @Provides
    @Singleton
    fun providesDatabase(context: Context): AppDatabase {
        return AppDatabase.createDatabase(context)
    }

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @Singleton
    fun providesFetchUseCase(context: Context): DataManagerImp {
        return DataManagerImp(
            provideCompositeDisposable(),
            ApiHelperImp(
                providesRemoteService(context)
            ),
            providesDatabase(context),
            providePrefHelper(
                AppPreferenceHelper(context, provideprefFileName())
            )
        )
    }

    @Provides
    @Singleton
    fun providesAppLanguage(context: Context): AppLanguageProvider {
        return AppLanguageProvider(context)
    }

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider

    @Singleton
    @Provides
    fun providesAnalyticsHelper(
        context: Context
    ): AnalyticsHelper = FirebaseAnalyticsHelper(context)

    @Provides
    fun providesDarkModeHelper(
        context: Context
    ): ThemeHelper =
        ThemeHelper(providePrefHelper(AppPreferenceHelper(context, provideprefFileName())))

}