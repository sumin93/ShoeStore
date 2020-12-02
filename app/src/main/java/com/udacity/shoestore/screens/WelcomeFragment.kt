package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_welcome,
                container,
                false
        )
        binding.buttonGoToTutorial.setOnClickListener(Navigation.createNavigateOnClickListener(
                WelcomeFragmentDirections.actionWelcomeFragmentToTutorialFragment()
        ))
        return binding.root
    }
}
