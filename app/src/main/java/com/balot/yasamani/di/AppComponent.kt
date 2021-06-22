package com.balot.yasamani.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AppModule::class, ActivitiesModule::class, FragmentsModule::class, AndroidSupportInjectionModule::class,ViewModelsModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}