package com.balot.yasamani.di

import com.balot.yasamani.view.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    internal abstract fun provideHomeFragment(): HomeFragment
}