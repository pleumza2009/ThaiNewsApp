package com.pleum.thainewsapp.ui.fragments

import android.app.Application
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.pleum.thainewsapp.R
import com.pleum.thainewsapp.adpaters.NewsAdapter
import com.pleum.thainewsapp.launchFragmentInHiltContainer
import com.pleum.thainewsapp.repositories.FakeNewsRepositoryAndroidTest
import com.pleum.thainewsapp.ui.NewsViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify


@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class NewsFragmentTest(){


    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    private   lateinit var  newsAdapter : NewsAdapter

    lateinit var   viewModel: NewsViewModel



    @Before
    fun setup (){
        hiltRule.inject()
    }


    @Test
    fun clickNewsArticle_NavigateToArticleFragment(){
        val navController = Mockito.mock(NavController::class.java)



        launchFragmentInHiltContainer<NewsFragment> {
            Navigation.setViewNavController(requireView(),navController)

        }

        onView(ViewMatchers.withId(R.id.rvBreakingNews)).perform(RecyclerViewActions.actionOnItemAtPosition<NewsAdapter.ViewHolder>(
            0,
            click()
        )
            )

        verify(navController).navigate(R.id.action_newsFragment_to_articleFragment)
    }


}




