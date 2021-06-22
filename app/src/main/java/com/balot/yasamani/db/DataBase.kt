package com.balot.yasamani.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.balot.yasamani.models.Article

@Database(entities = [Article::class],version = 1,exportSchema = false)
abstract class DataBase: RoomDatabase() {
    abstract fun getDao(): Dao
}