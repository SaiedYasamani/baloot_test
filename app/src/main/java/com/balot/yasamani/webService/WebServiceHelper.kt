package com.balot.yasamani.webService

import com.balot.yasamani.models.ArticlesResponse
import javax.inject.Inject

class WebServiceHelper @Inject constructor(val webServices: WebServices) {

    suspend fun getArticles(query: String): ArticlesResponse {
        return webServices.getArticles(
            query = query,
            from = "2021-05-22",
            sortBy = "publishedAt",
            apiKey = "9f8c35f795cf4705b0f3457be6c0f42b"
        )
    }
}