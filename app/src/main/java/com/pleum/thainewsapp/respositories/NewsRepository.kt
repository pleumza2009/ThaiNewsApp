package com.pleum.thainewsapp.respositories

import androidx.lifecycle.LiveData
import com.pleum.thainewsapp.models.Article
import com.pleum.thainewsapp.models.NewsResponse
import retrofit2.Response

interface NewsRepository    {
    suspend fun  getNews(countryCode:String,pageNumber:Int): Response<NewsResponse>

    suspend fun upsert(article: Article): Long

    fun getSavedNews(): LiveData<List<Article>>

    suspend fun deleteArticle(article:Article)

}