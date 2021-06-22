package com.balot.yasamani.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class ContextModule {
    @Binds
    internal abstract fun bindContext(applicationContext: Application): Context
}