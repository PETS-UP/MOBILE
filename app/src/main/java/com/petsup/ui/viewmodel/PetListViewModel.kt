package com.petsup.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petsup.api.Rest
import com.petsup.models.Pet
import com.petsup.services.PetService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.create

class PetListViewModel : ViewModel() {
    private var _petList = MutableLiveData<List<Pet>>()
    val petList: LiveData<List<Pet>> = _petList

    fun listPets(idCliente: Int) = viewModelScope.launch(Dispatchers.IO) {
        _petList.postValue(Rest.getInstance().create<PetService>().listPets(idCliente).execute().body())
    }

    fun deletePet(idPet: Int) = viewModelScope.launch(Dispatchers.IO) {
        TODO()
    }
}