package com.pleum.thainewsapp.respositories

import com.pleum.thainewsapp.api.NewsApi
import com.pleum.thainewsapp.db.ArticleDao
import com.pleum.thainewsapp.models.Article
import javax.inject.Inject

class DefaultNewsRepository @Inject constructor(
    val newsApi: NewsApi,
    val articleDao: ArticleDao
) : NewsRepository {

    override suspend fun getNews(countryCode: String, pageNumber: Int) = newsApi.getNews(countryCode,pageNumber)


    override suspend fun upsert(article: Article) = articleDao.upsert(article)


    override fun getSavedNews() = articleDao.getAllArticles()

   override suspend fun deleteArticle(article:Article) = articleDao.deleteArticle(article)
}