package com.pleum.thainewsapp.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.pleum.thainewsapp.api.NewsApi
import com.pleum.thainewsapp.db.ArticleDao
import com.pleum.thainewsapp.db.ArticleDatabase
import com.pleum.thainewsapp.respositories.DefaultNewsRepository
import com.pleum.thainewsapp.respositories.NewsRepository
import com.pleum.thainewsapp.util.Constants.BASE_URL
import com.pleum.thainewsapp.util.Constants.KEY_FIRST_NAME
import com.pleum.thainewsapp.util.Constants.KEY_FIRST_TIME_TOGGLE
import com.pleum.thainewsapp.util.Constants.KEY_LAST_NAME
import com.pleum.thainewsapp.util.Constants.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNewsApi (): NewsApi{
        return   Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(NewsApi::class.java)
    }


    @Singleton
    @Provides
    fun provideArticleDatabase(@ApplicationContext context:Context) = ArticleDatabase(context)


    @Singleton
    @Provides
    fun provideArticleDao(db : ArticleDatabase) = db.getArticleDao()



    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        api: NewsApi,
        dao: ArticleDao
    ) = DefaultNewsRepository(api,dao) as NewsRepository


    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context) =
            app.getSharedPreferences(SHARED_PREFERENCES_NAME,MODE_PRIVATE)


    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPref: SharedPreferences) = sharedPref.getBoolean(KEY_FIRST_TIME_TOGGLE,true)


}


