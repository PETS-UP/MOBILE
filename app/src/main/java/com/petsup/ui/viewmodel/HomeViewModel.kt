package com.petsup.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petsup.api.Rest
import com.petsup.models.petshop.Petshop
import com.petsup.services.PetshopService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.create

class HomeViewModel : ViewModel() {
    private var _petshopList = MutableLiveData<List<Petshop>>()
    val petshopList: LiveData<List<Petshop>> = _petshopList

    fun getPetshops() = viewModelScope.launch(Dispatchers.IO) {
        _petshopList.postValue(Rest.getInstance().create<PetshopService>().listPetshops().execute().body())
    }
}