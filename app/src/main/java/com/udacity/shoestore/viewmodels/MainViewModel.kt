package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    fun addShoe(shoe: Shoe) {
        val list = _shoeList.value?.toMutableList() ?: mutableListOf()
        list.add(shoe)
        _shoeList.value = list
    }

    init {
        val defaultList = mutableListOf<Shoe>()
        // Dummy data
        for (i in 0 until 10) {
            defaultList.add(
                Shoe(
                    name = "Name$i",
                    company = "Company$i",
                    description = "Description$i"
                )
            )
        }
        _shoeList.value = defaultList
    }
}
