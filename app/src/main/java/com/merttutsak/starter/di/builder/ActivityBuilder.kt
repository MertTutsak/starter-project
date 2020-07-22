package com.merttutsak.starter.di.builder


import com.merttutsak.starter.ui.main.MainActivityModule
import com.merttutsak.starter.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindMainActivity(): MainActivity

}