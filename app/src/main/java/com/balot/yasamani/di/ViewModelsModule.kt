package com.balot.yasamani.di

import com.balot.yasamani.viewModels.HomeViewModel
import com.balot.yasamani.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelsModule {

    @Provides
    @Singleton
    fun provideHomeVieModel(repository: Repository): HomeViewModel {
        return HomeViewModel(repository)
    }
}