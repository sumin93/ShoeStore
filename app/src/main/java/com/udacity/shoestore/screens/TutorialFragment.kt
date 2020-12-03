package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentTutorialBinding

class TutorialFragment : Fragment() {

    private lateinit var binding: FragmentTutorialBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_tutorial,
            container,
            false
        )
        binding.buttonAccept.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                TutorialFragmentDirections.actionTutorialFragmentToShoeListFragment()
            )
        )
        setHasOptionsMenu(false)
        return binding.root
    }
}
