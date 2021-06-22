package com.balot.yasamani.di

import android.content.Context
import androidx.room.Room
import com.balot.yasamani.BuildConfig
import com.balot.yasamani.db.DataBase
import com.balot.yasamani.repository.Repository
import com.balot.yasamani.webService.WebServiceHelper
import com.balot.yasamani.webService.WebServices
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
            .readTimeout(7000, TimeUnit.MILLISECONDS)
            .connectTimeout(7000, TimeUnit.MILLISECONDS)
        if (BuildConfig.DEBUG) builder.addInterceptor(interceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideWebServices(client: OkHttpClient): WebServices {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(WebServices::class.java)
    }

    @Provides
    @Singleton
    fun provideRoom(context: Context): DataBase {
        return Room.databaseBuilder(context, DataBase::class.java, "baloot_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideWebServiceHelper(webServices: WebServices): WebServiceHelper {
        return WebServiceHelper(webServices)
    }

    @Provides
    @Singleton
    fun provideRepository(webServicesHelper: WebServiceHelper, dataBase: DataBase): Repository {
        return Repository(webServicesHelper, dataBase)
    }
}