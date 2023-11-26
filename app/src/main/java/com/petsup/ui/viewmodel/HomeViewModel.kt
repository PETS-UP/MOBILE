package com.petsup.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petsup.api.Rest
import com.petsup.models.cliente.ClienteLocalizacao
import com.petsup.models.petshop.Petshop
import com.petsup.models.petshop.PetshopExibicao
import com.petsup.models.petshop.PetshopMediaAvaliacao
import com.petsup.models.petshop.PetshopMediaPreco
import com.petsup.services.ClienteService
import com.petsup.services.PetshopService
import com.petsup.ui.`object`.MapperObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class HomeViewModel : ViewModel() {
    private var _petshopList = MutableLiveData<List<PetshopExibicao>>()
    val petshopList: LiveData<List<PetshopExibicao>> = _petshopList

    private var _petshop = MutableLiveData<Petshop>()
    val petshop: LiveData<Petshop> = _petshop

    private var _clienteLocalizacao = MutableLiveData<ClienteLocalizacao>()
    val clienteLocalizacao: LiveData<ClienteLocalizacao> = _clienteLocalizacao

    private var _updateOperation = MutableLiveData<Unit>()
    val updateOperaion: LiveData<Unit> = _updateOperation

    private val apiCliente by lazy {
        Rest.getInstance().create(ClienteService::class.java)
    }

    private val apiPetshop by lazy {
        Rest.getInstance().create(PetshopService::class.java)
    }

    fun getPetshops() = viewModelScope.launch(Dispatchers.IO) {
        apiPetshop.listPetshops().enqueue(object : Callback<List<Petshop>> {
            override fun onResponse(call: Call<List<Petshop>>, response: Response<List<Petshop>>) {
                _petshopList.postValue(
                    MapperObject.listPetshopToListPetshopExibicao(response.body() ?: emptyList())
                )
            }

            override fun onFailure(call: Call<List<Petshop>>, t: Throwable) {
                Log.e("HOME VIEW MODEL", "Error fetching List Petshops")
            }

        })
    }

    fun updateCurrentLocation(idCliente: Int, latitude: Double, longitude: Double) = viewModelScope.launch(Dispatchers.IO) {
        _updateOperation.postValue(apiCliente.updateCurrentLocation(idCliente, latitude, longitude).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                Log.i("COORDINATES", "Success")
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.e("ERROR COORDINATES", "Error")
            }

        }))
    }

    fun getPetshopsByClienteBairro(idCliente: Int) = viewModelScope.launch(Dispatchers.IO) {
        apiPetshop.getPetshopsByClienteBairro(idCliente).enqueue(object : Callback<List<Petshop>> {
            override fun onResponse(call: Call<List<Petshop>>, response: Response<List<Petshop>>) {
                _petshopList.postValue(
                    MapperObject.listPetshopToListPetshopExibicao(response.body() ?: emptyList())
                )
            }

            override fun onFailure(call: Call<List<Petshop>>, t: Throwable) {
                Log.e("HOME VIEW MODEL", "Error fetching Petshops Proximos")
            }

        })
    }

    fun getPetshopsByMediaAvaliacao() = viewModelScope.launch(Dispatchers.IO) {
        apiPetshop.getPetshopsOrderByMedia().enqueue(object : Callback<List<PetshopMediaAvaliacao>> {
            override fun onResponse(
                call: Call<List<PetshopMediaAvaliacao>>,
                response: Response<List<PetshopMediaAvaliacao>>
            ) {
                _petshopList.postValue(
                    MapperObject.listPetshopAvaliacaoToListPetshopExibicao(response.body() ?: emptyList())
                )
            }

            override fun onFailure(call: Call<List<PetshopMediaAvaliacao>>, t: Throwable) {
                Log.e("HOME VIEW MODEL", "Error fetching Media Avaliacao")
            }

        })
    }

    fun getPetshopsByMediaPreco() = viewModelScope.launch(Dispatchers.IO) {
        apiPetshop.getPetshopsOrderByPrecoAsc().enqueue(object : Callback<List<PetshopMediaPreco>> {
            override fun onResponse(
                call: Call<List<PetshopMediaPreco>>,
                response: Response<List<PetshopMediaPreco>>
            ) {
                _petshopList.postValue(MapperObject.listPetshopPrecoToListPetshopExibicao(response.body() ?: emptyList()))
            }

            override fun onFailure(call: Call<List<PetshopMediaPreco>>, t: Throwable) {
                Log.e("HOME VIEW MODEL", "Error fetching Media Preco")
            }

        })
    }

    fun getPetshopById(idPetshop: Int) = viewModelScope.launch(Dispatchers.IO) {
        apiPetshop.getPetshopById(idPetshop).enqueue(object : Callback<Petshop> {
            override fun onResponse(call: Call<Petshop>, response: Response<Petshop>) {
                _petshop.postValue(response.body())
            }

            override fun onFailure(call: Call<Petshop>, t: Throwable) {
                Log.e("HOME VIEW MODEL", "Error fetching petshop")
            }

        })
    }
}