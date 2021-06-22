package com.balot.yasamani.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.balot.yasamani.models.Article

@Dao
interface Dao {
    @Insert
    suspend fun save(article: Article)

    @Query("SELECT * FROM article")
    suspend fun getArticles(): List<Article>
}