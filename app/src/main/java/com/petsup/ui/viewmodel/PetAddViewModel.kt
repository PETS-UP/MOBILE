package com.petsup.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petsup.api.Rest
import com.petsup.models.pet.PetResposta
import com.petsup.services.PetService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PetAddViewModel: ViewModel() {
    private var _petStack = MutableLiveData<List<PetResposta>>()
    val petStack: LiveData<List<PetResposta>> = _petStack

    private val api by lazy {
        Rest.getInstance().create(PetService::class.java)
    }

    fun postPetStack(obj: String) = viewModelScope.launch(Dispatchers.IO) {
        val request = api.addToStack(obj)
        request.execute()
    }

    fun popFromStack() = viewModelScope.launch(Dispatchers.IO) {
        val request = api.popFromStack()
        request.execute()
    }

    fun postPet() = viewModelScope.launch(Dispatchers.IO) {
        val request = api.postPet()
        request.execute()
    }

}