package com.pleum.thainewsapp.di

import android.content.Context
import androidx.room.Room
import com.pleum.thainewsapp.db.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {


    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context : Context) =
        Room.inMemoryDatabaseBuilder(context,ArticleDatabase::class.java)
            .allowMainThreadQueries()
            .build()
}