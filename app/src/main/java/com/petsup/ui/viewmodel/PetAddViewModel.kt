package com.petsup.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petsup.api.Rest
import com.petsup.models.pet.PetResposta
import com.petsup.services.PetService
import com.petsup.services.PetshopService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.create

class PetAddViewModel: ViewModel() {
    private var _petStack = MutableLiveData<List<PetResposta>>()
    val petStack: LiveData<List<PetResposta>> = _petStack

    private val api by lazy {
        Rest.getInstance().create<PetService>()
    }

    fun postPetStack(obj: String) = viewModelScope.launch(Dispatchers.IO) {
        api.addToStack(obj)
    }

    fun popFromStack() = viewModelScope.launch(Dispatchers.IO) {
        api.popFromStack()
    }

    fun clearStack() = viewModelScope.launch(Dispatchers.IO) {
        api.clearStack()
    }

    fun postPet(idCliente: Int) = viewModelScope.launch(Dispatchers.IO) {
        api.postPet(idCliente).execute()
    }
}