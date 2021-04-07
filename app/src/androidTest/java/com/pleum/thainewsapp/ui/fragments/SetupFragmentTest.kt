package com.pleum.thainewsapp.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.pleum.thainewsapp.R
import com.pleum.thainewsapp.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*


@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class SetupFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)







    @Before
    fun setup (){
        hiltRule.inject()
    }

    


    @Test
    fun clickContinueButton_navigateToNewsFragmentWhenDataIsWritingAndNotFirstAppOpen(){
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<SetupFragment> {
            Navigation.setViewNavController(requireView(),navController)

        }

        onView(withId(R.id.tvContinue)).perform(click())

        verify(navController).navigate(SetupFragmentDirections.actionSetupFragmentToNewsFragment())
    }





}