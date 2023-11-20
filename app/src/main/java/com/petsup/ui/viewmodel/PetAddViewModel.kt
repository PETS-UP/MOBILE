package com.petsup.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.petsup.api.Rest
import com.petsup.models.pet.PetResposta
import com.petsup.services.PetService

class PetAddViewModel: ViewModel() {
    private var _petStack = MutableLiveData<List<PetResposta>>()
    val petStack: LiveData<List<PetResposta>> = _petStack

    private val api by lazy {
        Rest.getInstance().create(PetService::class.java)
    }

    fun postPetStack(obj: String) {
        val request = api.addToStack(obj)
        request.execute()
    }

    fun popFromStack() {
        val request = api.popFromStack()
        request.execute()
    }

    fun postPet() {
        val request = api.postPet()
        request.execute()
    }

}