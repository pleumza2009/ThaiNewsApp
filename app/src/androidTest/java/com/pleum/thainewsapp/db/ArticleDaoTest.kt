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
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import java.io.IOException


@RunWith(AndroidJUnit4::class)
@SmallTest
class ArticleDaoTest {

    @get:Rule
    var instantTaskExcutorRule = InstantTaskExecutorRule()

    private lateinit var database: ArticleDatabase
    private  lateinit var  dao: ArticleDao

    private lateinit var context:Context

    private lateinit var sampleArticle : Article



    @Before
    fun setup(){
        context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                ArticleDatabase::class.java,
        ).allowMainThreadQueries().build()
        dao = database.getArticleDao()

         sampleArticle = getArticle(context,"data.json")!!

    }

    @After
    fun teardown(){
        database.close()
    }

    fun getArticle(context:Context, fileName:String): Article? {
        val jStr: String
        try {
            jStr = context.assets.open(fileName)
                    .bufferedReader().use { it.readText() }
            val gson = Gson()
            val articleType = object : TypeToken<Article>() {}.type
            return gson.fromJson(jStr, articleType)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
    }

    @Test
    fun upsertArticle() = runBlockingTest {
        dao.upsert(sampleArticle)
        val allArticles = dao.getAllArticles().getOrAwaitValue()
        assertThat(allArticles).contains(sampleArticle)

        }





}