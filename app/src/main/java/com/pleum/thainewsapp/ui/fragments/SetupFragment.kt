package com.pleum.thainewsapp.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.pleum.thainewsapp.R
import com.pleum.thainewsapp.databinding.FragmentSetupBinding
import com.pleum.thainewsapp.util.Constants.KEY_FIRST_NAME
import com.pleum.thainewsapp.util.Constants.KEY_FIRST_TIME_TOGGLE
import com.pleum.thainewsapp.util.Constants.KEY_LAST_NAME
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SetupFragment : Fragment(R.layout.fragment_setup) {

    lateinit var binding: FragmentSetupBinding

    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var  sharedPref : SharedPreferences

    @set:Inject
    var isFirstAppOpen = true


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSetupBinding.bind(view)




        if (!isFirstAppOpen){
            val navOption =  NavOptions.Builder()
                    .setPopUpTo(R.id.setupFragment,true)
                    .build()
            findNavController().navigate(
                    R.id.action_setupFragment_to_newsFragment,
                    savedInstanceState,
                    navOption
            )
        }

        binding.tvContinue.setOnClickListener{
            val success = writePersonalDataToSharedPref()
            if (success) {
                findNavController().navigate(R.id.action_setupFragment_to_newsFragment)
            }else {
                Snackbar.make(requireView(), "Please enter all the fields", Snackbar.LENGTH_SHORT).show()
            }
        }


    }



    private fun writePersonalDataToSharedPref() : Boolean {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastname.text.toString()
        if (firstName.isEmpty() || lastName.isEmpty()){
            return false
        }
        sharedPreferences.edit()
                .putString(KEY_FIRST_NAME, firstName)
                .putString(KEY_LAST_NAME, lastName)
                .putBoolean(KEY_FIRST_TIME_TOGGLE, false)
                .apply()


        return true
    }



}


