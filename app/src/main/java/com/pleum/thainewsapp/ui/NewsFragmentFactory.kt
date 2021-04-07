package com.pleum.thainewsapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.pleum.thainewsapp.adpaters.NewsAdapter
import com.pleum.thainewsapp.ui.fragments.NewsFragment
import javax.inject.Inject

class NewsFragmentFactory : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
            return  when(className){

                else -> super.instantiate(classLoader, className)
            }


    }
}