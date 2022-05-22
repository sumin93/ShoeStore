package com.udacity.shoestore.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.viewmodels.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentAddShoeBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.AddShoeViewModel

class AddShoeFragment : Fragment() {

    private lateinit var binding: FragmentAddShoeBinding
    private lateinit var activityViewModel: MainViewModel
    private lateinit var fragmentViewModel: AddShoeViewModel
    private lateinit var navController: NavController

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            fragmentViewModel.resetErrors()
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_add_shoe,
            container,
            false
        )
        navController = findNavController()
        fragmentViewModel = ViewModelProvider(this)[AddShoeViewModel::class.java]
        fragmentViewModel.dataToSave.observe(viewLifecycleOwner) {
            activityViewModel.addShoe(it)
            navController.navigateUp()
        }
        with(binding) {
            // reset errors when typing
            editTextName.addTextChangedListener(textWatcher)
            editTextCompany.addTextChangedListener(textWatcher)
            editTextDescription.addTextChangedListener(textWatcher)

            // show/hide errors when input have been validate
            fragmentViewModel.nameIsCorrect.observe(viewLifecycleOwner) {
                textInputLayoutName.error = if (!it) {
                    getString(R.string.error_field_must_not_be_empty)
                } else {
                    null
                }
            }
            fragmentViewModel.descriptionIsCorrect.observe(viewLifecycleOwner) {
                textInputLayoutDescription.error = if (!it) {
                    getString(R.string.error_field_must_not_be_empty)
                } else {
                    null
                }
            }
            fragmentViewModel.companyIsCorrect.observe(viewLifecycleOwner) {
                textInputLayoutCompany.error = if (!it) {
                    getString(R.string.error_field_must_not_be_empty)
                } else {
                    null
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityViewModel = activityViewModels<MainViewModel>().value
        binding.viewModel = fragmentViewModel
        binding.shoe = Shoe("",0.0,"","")
        binding.buttonCancel.setOnClickListener {
            navController.navigateUp()
        }
    }
}
