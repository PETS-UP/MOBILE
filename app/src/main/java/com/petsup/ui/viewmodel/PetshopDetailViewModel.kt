package com.petsup.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petsup.api.Rest
import com.petsup.models.petshop.Petshop
import com.petsup.models.servico.ServicoResposta
import com.petsup.services.PetshopService
import com.petsup.services.ServicoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PetshopDetailViewModel : ViewModel() {
    private var _serviceList = MutableLiveData<List<ServicoResposta>>()
    val serviceList: LiveData<List<ServicoResposta>> = _serviceList

    private var _petshop = MutableLiveData<Petshop>()
    val petshop: LiveData<Petshop> = _petshop

    private val apiPetshop by lazy {
        Rest.getInstance().create(PetshopService::class.java)
    }

    private val apiServico by lazy {
        Rest.getInstance().create(ServicoService::class.java)
    }

    fun getServices(idPetshop: Int) = viewModelScope.launch(Dispatchers.IO) {
        apiServico.getServicosByIdPetshop(idPetshop).enqueue(object : Callback<List<ServicoResposta>> {
            override fun onResponse(
                call: Call<List<ServicoResposta>>,
                response: Response<List<ServicoResposta>>
            ) {
                _serviceList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<ServicoResposta>>, t: Throwable) {
                Log.e("DETAIL", "Error fetching services at PetshopDetail")
            }

        })
    }

    fun getPetshopById(idPetshop: Int) = viewModelScope.launch(Dispatchers.IO) {
        apiPetshop.getPetshopById(idPetshop)
            .enqueue(object : Callback<Petshop> {
                override fun onResponse(call: Call<Petshop>, response: Response<Petshop>) {
                    _petshop.postValue(response.body())
                }

                override fun onFailure(call: Call<Petshop>, t: Throwable) {
                    Log.e("DETAIL", "Error fetching petshop at PetshopDetail")
                }

            })
    }
}