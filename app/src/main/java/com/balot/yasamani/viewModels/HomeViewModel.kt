package com.balot.yasamani.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balot.yasamani.models.Article
import com.balot.yasamani.models.ArticlesResponse
import com.balot.yasamani.repository.Repository
import kotlinx.coroutines.launch
import com.balot.yasamani.models.Result
import javax.inject.Inject

class HomeViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val articlesLiveData = MutableLiveData<Result<List<Article>>>()

    fun getArticles(query: String) {
        viewModelScope.launch {
            val articles = repository.getArticles()
            if (articles.isEmpty()) {
                requestArticles(query)
            } else {
                articlesLiveData.value = Result.Success(articles, null);
            }
        }
    }

    private fun requestArticles(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.requestArticles(query)
                if (response.status.equals("ok")) {
                    refreshDB(response.articles)
                    getArticles(query)
                } else {
                    articlesLiveData.value = Result.Failure(null, response.message)
                }
            } catch (e: Exception) {
                articlesLiveData.value = Result.Error(null, e.message)
            }
        }
    }

    private fun refreshDB(articles: List<ArticlesResponse.Articles>?) {
        val localVersionArticles = articles?.map { article -> createLocalVersionArticle(article) }
        viewModelScope.launch {
            localVersionArticles?.map { localArticle -> repository.saveArticle(localArticle) }
        }
    }

    private fun createLocalVersionArticle(webArticle: ArticlesResponse.Articles): Article {
        return Article(
            source = Article.Source(
                id = webArticle.source?.id ?: "",
                name = webArticle.source?.name ?: ""
            ),
            author = webArticle.author ?: "",
            content = webArticle.content ?: "",
            description = webArticle.description ?: "",
            publishedAt = webArticle.publishedAt ?: "",
            title = webArticle.title ?: "",
            url = webArticle.url ?: "",
            urlToImage = webArticle.url ?: ""
        )
    }

    fun getArticleLiveData(): LiveData<Result<List<Article>>> = articlesLiveData
}