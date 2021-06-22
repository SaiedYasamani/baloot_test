package com.balot.yasamani.repository

import com.balot.yasamani.db.DataBase
import com.balot.yasamani.models.Article
import com.balot.yasamani.models.ArticlesResponse
import com.balot.yasamani.webService.WebServiceHelper
import javax.inject.Inject

class Repository @Inject constructor(
    val webServicesHelper: WebServiceHelper,
    val db: DataBase
) {

    suspend fun getArticles():List<Article> {
        return db.getDao().getArticles()
    }

    suspend fun saveArticle(article: Article){
        db.getDao().save(article)
    }

    suspend fun requestArticles(query: String):ArticlesResponse {
        return webServicesHelper.getArticles(query)
    }
}