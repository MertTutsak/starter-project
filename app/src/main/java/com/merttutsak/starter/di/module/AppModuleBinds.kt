package com.merttutsak.starter.di.module

import com.merttutsak.starter.app.initializers.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class AppModuleBinds {

    @IntoSet
    @Binds
    abstract fun provideLogger(bind: LoggerInitializer): AppInitializer

    @IntoSet
    @Binds
    abstract fun provideStetho(bind: StethoInitializer): AppInitializer

}