package com.pleum.thainewsapp.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pleum.thainewsapp.R
import com.pleum.thainewsapp.databinding.FragmentSettingBinding
import com.pleum.thainewsapp.util.Constants
import com.pleum.thainewsapp.util.Constants.KEY_FIRST_NAME
import com.pleum.thainewsapp.util.Constants.KEY_LAST_NAME
import com.pleum.thainewsapp.util.Constants.SHARED_PREFERENCES_NAME
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : Fragment(R.layout.fragment_setting) {

    lateinit var binding: FragmentSettingBinding





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingBinding.bind(view)

        val sharedPreferences = activity?.getSharedPreferences(SHARED_PREFERENCES_NAME,Context.MODE_PRIVATE)
        val saveFirstName = sharedPreferences?.getString(KEY_FIRST_NAME,null)
        val saveLastName = sharedPreferences?.getString(KEY_LAST_NAME,null)


        binding.tvFirstName.text = saveFirstName
        binding.tvLastName.text = saveLastName








    }



}