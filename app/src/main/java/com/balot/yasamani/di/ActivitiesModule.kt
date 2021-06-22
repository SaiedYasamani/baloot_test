package com.balot.yasamani.di

import com.balot.yasamani.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    internal abstract fun provideManiActivity(): MainActivity
}