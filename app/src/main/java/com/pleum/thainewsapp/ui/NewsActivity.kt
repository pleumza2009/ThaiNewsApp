package com.pleum.thainewsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.pleum.thainewsapp.R
import com.pleum.thainewsapp.adpaters.NewsAdapter
import com.pleum.thainewsapp.databinding.NewsActivityBinding
import com.pleum.thainewsapp.ui.fragments.NewsFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {




   private lateinit var binding: NewsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment

        binding.bottomNavigationView.setupWithNavController(navHostFragment.navController)
    }


}