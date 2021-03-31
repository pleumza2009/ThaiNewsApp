package com.pleum.thainewsapp.api



import com.pleum.thainewsapp.BuildConfig
import com.pleum.thainewsapp.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun  getNews(
        @Query("country")
        countryCode:String = "th",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ): Response<NewsResponse>
}