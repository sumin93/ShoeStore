package com.udacity.shoestore

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
        _shoeList.value = listOf(
            Shoe(
                "name1",
                10.0,
                "company",
                "description"
            )
        )
    }
}
