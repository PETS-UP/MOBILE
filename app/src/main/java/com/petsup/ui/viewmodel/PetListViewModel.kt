package com.petsup.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petsup.api.Rest
import com.petsup.models.pet.PetCadastro
import com.petsup.services.PetService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.create

class PetListViewModel : ViewModel() {
    private var _petCadastroList = MutableLiveData<List<PetCadastro>>()
    val petCadastroList: LiveData<List<PetCadastro>> = _petCadastroList

    fun listPets(idCliente: Int) = viewModelScope.launch(Dispatchers.IO) {
        _petCadastroList.postValue(Rest.getInstance().create<PetService>().listPets(idCliente).execute().body())
    }

    fun deletePet(idPet: Int) = viewModelScope.launch(Dispatchers.IO) {
        TODO()
    }
}