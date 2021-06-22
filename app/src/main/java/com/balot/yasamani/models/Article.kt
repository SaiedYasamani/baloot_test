package com.balot.yasamani.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class Article(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @Embedded val source: Source,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
) {
    data class Source(
        @ColumnInfo(name = "sourceId") val id: String?,
        val name: String?
    )
}