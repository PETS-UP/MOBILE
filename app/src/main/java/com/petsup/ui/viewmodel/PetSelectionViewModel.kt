package com.petsup.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.petsup.api.Rest
import com.petsup.models.pet.PetResposta
import com.petsup.services.PetService
import retrofit2.create

class PetSelectionViewModel : ViewModel() {
    private var _petList = MutableLiveData<List<PetResposta>>()
    val petList: LiveData<List<PetResposta>> = _petList

    fun getPets(idCliente: Int) {
        _petList.postValue(Rest.getInstance().create<PetService>().listPets(idCliente).execute().body())
    }
}