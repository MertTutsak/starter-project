package com.merttutsak.starter.di.component

import com.merttutsak.starter.app.App
import com.merttutsak.starter.di.builder.ActivityBuilder
import com.merttutsak.starter.di.builder.FragmentBuilder
import com.merttutsak.starter.di.module.AppModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}