package com.petsup.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.petsup.models.Pet

class PetListViewModel : ViewModel() {
    private var _petList = MutableLiveData<List<Pet>>()
    val petList: LiveData<List<Pet>> = _petList
}