package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class AddShoeViewModel : ViewModel() {

    private val _dataToSave = MutableLiveData<Shoe>()
    val dataToSave: LiveData<Shoe>
        get() = _dataToSave

    private val _nameIsCorrect = MutableLiveData<Boolean>()
    val nameIsCorrect: LiveData<Boolean>
        get() = _nameIsCorrect

    private val _descriptionIsCorrect = MutableLiveData<Boolean>()
    val descriptionIsCorrect: LiveData<Boolean>
        get() = _descriptionIsCorrect

    private val _companyIsCorrect = MutableLiveData<Boolean>()
    val companyIsCorrect: LiveData<Boolean>
        get() = _companyIsCorrect

    fun resetErrors() {
        _nameIsCorrect.value = true
        _descriptionIsCorrect.value = true
        _companyIsCorrect.value = true
    }

    fun saveData(
        shoe: Shoe
    ) {
        val (name, company, description) = shoe
        val nameIsCorrect = name.isNotBlank()
        _nameIsCorrect.value = nameIsCorrect
        val descriptionIsCorrect = description.isNotBlank()
        _descriptionIsCorrect.value = descriptionIsCorrect
        val companyIsCorrect = company.isNotBlank()
        _companyIsCorrect.value = companyIsCorrect

        val dataIsReady = nameIsCorrect && descriptionIsCorrect && companyIsCorrect

        if (dataIsReady) {
            _dataToSave.value = shoe
        }
    }
}
