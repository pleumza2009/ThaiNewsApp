package com.pleum.thainewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pleum.thainewsapp.models.Article


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>


    @Delete
    suspend fun deleteArticle(article: Article)
}