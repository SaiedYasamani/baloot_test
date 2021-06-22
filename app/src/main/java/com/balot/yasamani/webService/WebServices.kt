package com.balot.yasamani.webService

import com.balot.yasamani.models.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("everything")
    suspend fun getArticles(
        @Query("q") query:String,
        @Query("from") from:String,
        @Query("sortBy") sortBy:String,
        @Query("apikey") apiKey:String
    ): ArticlesResponse
}