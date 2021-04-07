package com.pleum.thainewsapp.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pleum.thainewsapp.models.Article
import com.pleum.thainewsapp.models.NewsResponse
import com.pleum.thainewsapp.respositories.NewsRepository
import retrofit2.Response

class FakeNewsRepositoryAndroidTest : NewsRepository{

    private val articles = mutableListOf<Article>()

    private val getSaveNews = MutableLiveData<List<Article>>(articles)

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError (value : Boolean){
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData(){
        getSaveNews.postValue(articles)
    }


    override suspend fun getNews(countryCode: String, pageNumber: Int): Response<NewsResponse>? {
        return null
    }

    override suspend fun upsert(article: Article) {
        articles.add(article)
        refreshLiveData()
    }

    override fun getSavedNews(): LiveData<List<Article>> {
        return getSaveNews
    }

    override suspend fun deleteArticle(article: Article) {
        articles.remove(article)
        refreshLiveData()
    }
}