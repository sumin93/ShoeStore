package com.udacity.shoestore.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _isAuthorized = MutableLiveData<Boolean>()
    val isAuthorized: LiveData<Boolean>
        get() = _isAuthorized

    private val _isLoginFieldCorrect = MutableLiveData<Boolean>()
    val isLoginFieldCorrect: LiveData<Boolean>
        get() = _isLoginFieldCorrect

    private val _isPasswordFieldCorrect = MutableLiveData<Boolean>()
    val isPasswordFieldCorrect: LiveData<Boolean>
        get() = _isPasswordFieldCorrect

    fun authorize(login: String, password: String) {
        _isAuthorized.value = validateFields(login, password)
    }

    fun correctPassword() {
        _isPasswordFieldCorrect.value = true
    }

    fun correctLogin() {
        _isLoginFieldCorrect.value = true
    }

    fun authorizedStatusProcessed() {
        _isAuthorized.value = false
    }

    private fun validateFields(login: String, password: String): Boolean {
        val loginValid = login.trim().length >= MIN_LOGIN_LENGTH
        val passwordValid = password.trim().length >= MIN_PASSWORD_LENGTH
        _isLoginFieldCorrect.value = loginValid
        _isPasswordFieldCorrect.value = passwordValid
        return loginValid && passwordValid
    }

    companion object {
        private const val MIN_LOGIN_LENGTH = 6
        private const val MIN_PASSWORD_LENGTH = 6
    }
}
