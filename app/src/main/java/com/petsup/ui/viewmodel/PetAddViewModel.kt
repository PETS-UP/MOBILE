package com.petsup.ui.viewmodel

import android.util.Log
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class PetAddViewModel: ViewModel() {
    private var _petStack = MutableLiveData<List<PetResposta>>()
    val petStack: LiveData<List<PetResposta>> = _petStack

    private var _step = MutableLiveData<Unit>()
    val step:LiveData<Unit> = _step

    private var _lastStep = MutableLiveData<Unit>()
    val lastStep:LiveData<Unit> = _lastStep

    private val api by lazy {
        Rest.getInstance().create<PetService>()
    }

    fun postPetStack(obj: String) = viewModelScope.launch(Dispatchers.IO) {
        api.addToStack(obj).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    _step.postValue(Unit)
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                print("Erro ao adicionar atributo na pilha.")
            }
        })
    }

    fun popFromStack() = viewModelScope.launch(Dispatchers.IO) {
        api.popFromStack().enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    Log.i("OK", "OK")
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                print("Erro ao voltar.")
            }
        })
    }

    fun postPet(idCliente: Int) = viewModelScope.launch(Dispatchers.IO) {
        api.postPet(idCliente).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    _lastStep.postValue(Unit)
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                print("Erro ao cadastrar pet.")
            }
        })
    }

    fun clearStack() = viewModelScope.launch(Dispatchers.IO) {
        api.clearStack().enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    Log.i("OK", "OK")
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                print("Erro ao limpar pilha.")
            }
        })
    }
}