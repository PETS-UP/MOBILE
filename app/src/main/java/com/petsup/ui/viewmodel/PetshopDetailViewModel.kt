package com.petsup.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.petsup.api.Rest
import com.petsup.models.servico.ServicoResposta
import com.petsup.services.ServicoService
import retrofit2.create

class PetshopDetailViewModel : ViewModel() {
    private var _serviceList = MutableLiveData<List<ServicoResposta>>()
    val serviceList: LiveData<List<ServicoResposta>> = _serviceList

    fun getServices(idPetshop: Int) {
        _serviceList.postValue(Rest.getInstance().create<ServicoService>().getServicosByIdPetshop(idPetshop).execute().body())
    }
}