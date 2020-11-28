package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isLoginFieldCorrect.observe(viewLifecycleOwner) { correct ->
            binding.textInputLayoutLogin.error = if (correct) {
                null
            } else {
                getString(R.string.error_login_text)
            }
        }
        viewModel.isPasswordFieldCorrect.observe(viewLifecycleOwner) { correct ->
            binding.textInputLayoutPassword.error = if (correct) {
                null
            } else {
                getString(R.string.error_password_text)
            }
        }
        viewModel.isAuthorized.observe(viewLifecycleOwner) { authorized ->
            if (authorized) {
                Toast.makeText(requireContext(), "AUTH", Toast.LENGTH_SHORT).show()
                viewModel.authorizedStatusProcessed()
            }
        }
        with(binding) {
            editTextLogin.addTextChangedListener {
                viewModel.correctLogin()
            }
            editTextPassword.addTextChangedListener {
                viewModel.correctPassword()
            }
            buttonSignIn.setOnClickListener {
                authorize()
            }
            buttonSignUp.setOnClickListener {
                authorize()
            }
        }
    }

    private fun authorize() {
        val login = binding.editTextLogin.text?.toString() ?: ""
        val password = binding.editTextPassword.text?.toString() ?: ""
        viewModel.authorize(login, password)
    }
}
