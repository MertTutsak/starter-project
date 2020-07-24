package com.merttutsak.starter.ui.main


import com.merttutsak.starter.di.scope.ViewModelScope
import dagger.Module

@Module
abstract class MainActivityModule {

    @ViewModelScope(MainViewModel::class)
    internal abstract fun provideMainViewModel(viewModel: MainViewModel): MainViewModel

}


