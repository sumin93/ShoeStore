package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeBinding

class ShoeListFragment : Fragment() {

    private lateinit var activityViewModel: MainViewModel
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )
        binding.floatingActionButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                ShoeListFragmentDirections.actionShoeListFragmentToAddShoeFragment()
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityViewModel = activityViewModels<MainViewModel>().value
        activityViewModel.shoeList.observe(viewLifecycleOwner) {
            with(binding) {
                linearLayoutContainerShoes.removeAllViews()
                for (shoe in it) {
                    val shoeItemBinding = DataBindingUtil.inflate<ItemShoeBinding>(
                        layoutInflater,
                        R.layout.item_shoe,
                        linearLayoutContainerShoes,
                        true
                    )
                    shoeItemBinding.shoe = shoe
                }
            }
        }
    }
}
