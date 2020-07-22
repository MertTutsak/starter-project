package com.merttutsak.starter.ui.main


import com.merttutsak.starter.di.ViewModelKey
import dagger.Module

@Module
abstract class MainActivityModule {

    @ViewModelKey(MainViewModel::class)
    internal abstract fun provideMainViewModel(viewModel: MainViewModel): MainViewModel

}


