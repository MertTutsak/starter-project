package com.merttutsak.starter.di.component

import com.merttutsak.starter.app.App
import com.merttutsak.starter.di.builder.ActivityBuilder
import com.merttutsak.starter.di.builder.FragmentBuilder
import com.merttutsak.starter.di.module.AppModule
import com.merttutsak.starter.di.module.LocalDBModule
import com.merttutsak.starter.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class,
        AppModule::class,
        NetworkModule::class,
        LocalDBModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    override fun inject(instance: App?)


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

}