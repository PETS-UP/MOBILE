package com.petsup.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petsup.api.Rest
import com.petsup.models.pet.PetResposta
import com.petsup.services.PetService
import com.petsup.ui.model.PetListViewHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PetListViewModel : ViewModel() {
    private var _petList = MutableLiveData<List<PetResposta>>()
    val petList: LiveData<List<PetResposta>> = _petList

    private var _state = MutableLiveData<PetListViewHolder>()
    val state: LiveData<PetListViewHolder> = _state

    private val api by lazy {
        Rest.getInstance().create(PetService::class.java)
    }

    fun listPets(idCliente: Int) = viewModelScope.launch(Dispatchers.IO) {
        val request = api.listPets(idCliente)

        request.enqueue(object : Callback<List<PetResposta>> {
            override fun onResponse(
                call: Call<List<PetResposta>>,
                response: Response<List<PetResposta>>
            ) {
                if (response.isSuccessful) {
                    _petList.postValue(response.body())
                    _state.postValue(if (response.body()?.isEmpty() == true) PetListViewHolder.EmptyPetList() else PetListViewHolder.PetList())
                }
            }

            override fun onFailure(call: Call<List<PetResposta>>, t: Throwable) {
                Log.e("PET LIST VIEW MODEL", "Error fetching pets: $t")
            }

        })
    }

    fun deletePet(idPet: Int, idCliente: Int) = viewModelScope.launch(Dispatchers.IO) {
        val request = api.deletePet(idPet)

        request.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    listPets(idCliente)
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.e("PET LIST VIEW MODEL", "Error deleting pet: $t")
            }
        })
    }
}