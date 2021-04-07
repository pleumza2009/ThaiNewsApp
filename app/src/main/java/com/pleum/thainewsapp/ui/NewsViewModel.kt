package com.pleum.thainewsapp.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pleum.thainewsapp.NewsApplication
import com.pleum.thainewsapp.models.Article
import com.pleum.thainewsapp.models.NewsResponse
import com.pleum.thainewsapp.respositories.DefaultNewsRepository
import com.pleum.thainewsapp.respositories.NewsRepository
import com.pleum.thainewsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class NewsViewModel @ViewModelInject constructor(
        app: Application,
        val newsRepository: NewsRepository
): AndroidViewModel(app) {

    val News: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var NewsPage = 1
    var NewsResponse: NewsResponse? = null



    init {
        getNews("th")
    }

    fun getNews(countryCode: String) = viewModelScope.launch {
        safeNewsCall(countryCode)
    }




    private  fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                NewsPage++
                if (NewsResponse == null){
                    NewsResponse = resultResponse
                }else {
                    val oldArticle = NewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticle?.addAll(newArticles)
                }
                return  Resource.Success(NewsResponse ?: resultResponse)
            }
        }
        return  Resource.Error(response.message())
    }




    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getSaveNews() = newsRepository.getSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }



    private suspend fun  safeNewsCall(countryCode: String){
        News.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = newsRepository.getNews(countryCode, NewsPage)
                News.postValue(handleBreakingNewsResponse(response!!))
            }else{
                News.postValue(Resource.Error("No internet connection"))
            }
        }catch (t:Throwable){
            when(t){
                is IOException -> News.postValue(Resource.Error("Network Failure"))
                else -> News.postValue(Resource.Error("Conversion Error"))
            }

        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<NewsApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork ?: return  false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return  when{
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }else{
            connectivityManager.activeNetworkInfo?.run {
                return when(type){
                    TYPE_WIFI -> true
                    TYPE_MOBILE ->true
                    TYPE_ETHERNET-> true
                    else -> false
                }
            }
        }
        return  false
    }
}