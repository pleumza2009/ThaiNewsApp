package com.pleum.thainewsapp.db

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pleum.thainewsapp.getOrAwaitValue
import com.pleum.thainewsapp.models.Article
import com.pleum.thainewsapp.models.Source
import com.pleum.thainewsapp.ui.fragments.ArticleFragmentArgs
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named


@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ArticleDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExcutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
     lateinit var database: ArticleDatabase

    private  lateinit var  dao: ArticleDao


    private lateinit var context:Context
    private lateinit var sampleArticle : Article



    @Before
    fun setup(){

        context = ApplicationProvider.getApplicationContext()

        hiltRule.inject()
        dao = database.getArticleDao()


    }

    @After
    fun teardown(){
        database.close()
    }






}