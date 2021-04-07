package com.pleum.thainewsapp.ui

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.google.common.reflect.TypeToken
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.pleum.thainewsapp.MainCoroutineRuleAndroidTest
import com.pleum.thainewsapp.getOrAwaitValue
import com.pleum.thainewsapp.models.Article
import com.pleum.thainewsapp.models.Source
import com.pleum.thainewsapp.repositories.FakeNewsRepositoryAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import java.io.IOException

@ExperimentalCoroutinesApi
class NewsViewModelAndroidTest {

    @get:Rule
        var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
        var mainCoroutine = MainCoroutineRuleAndroidTest()


    private lateinit var viewModel: NewsViewModel


    private lateinit var sampleArticle : Article
    private lateinit var context: Context



    @Before
    fun setup (){
        val app = mock(Application::class.java)
         context = ApplicationProvider.getApplicationContext()
        viewModel = NewsViewModel(app,FakeNewsRepositoryAndroidTest())


        sampleArticle = getArticle(context,"data.json")!!
    }

    fun getArticle(context: Context, fileName:String): Article? {
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
    fun saveArticle_ToDatabase(){
        /* วิธีนี้ใช้ได้
        sampleArticle = Article(null,"Matichon","“”  “ LGBTQ+ \n" +
                " 1 ..  “”   29 ..  “ ”   “ ”  LGBTQ \n" +
                "  ! LGBTQ+ \n" +
                " “”","“ครูธัญ”  ไม่ตลก “ซูโม่กิ๊ก พูดติดตลก …","2021-04-01T07:11:15Z",
                Source(null,"Matichon.co.th"),"ครูธัญ' สวด 'ซูโม่กิ๊กคะ ไม่ตลกด้วย!' หลังพูดติดตลกมุขอคติทางเพศ กลางรายการ - มติชน",
                "https://www.matichon.co.th/politics/news_2652758","https://www.matichon.co.th/wp-content/uploads/2021/04/11ีัส.jpg")

         */
        viewModel.saveArticle(sampleArticle)

        val getValue = viewModel.getSaveNews().getOrAwaitValue()

        assertThat(getValue).contains(sampleArticle)

    }

    @Test
    fun deleteArticle_FromDatabase(){
        viewModel.saveArticle(sampleArticle)
        viewModel.deleteArticle(sampleArticle)

        val getValue = viewModel.getSaveNews().getOrAwaitValue()

        assertThat(getValue).doesNotContain(sampleArticle)
    }



}