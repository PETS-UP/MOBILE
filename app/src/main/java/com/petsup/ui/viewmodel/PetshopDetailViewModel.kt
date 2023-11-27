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
import com.petsup.models.cliente.ClienteDetalhes
import com.petsup.services.ClienteService
import com.petsup.services.FavoritoService
import com.petsup.services.ServicoService
import com.petsup.ui.model.BookingConfirmationViewHolder
import com.petsup.ui.model.PetshopDetailViewHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.create
import retrofit2.await

class PetshopDetailViewModel : ViewModel() {
    private var _state = MutableLiveData<PetshopDetailViewHolder>()
    val state: LiveData<PetshopDetailViewHolder> = _state

    private var _serviceList = MutableLiveData<List<ServicoResposta>>()
    val serviceList: LiveData<List<ServicoResposta>> = _serviceList

    private var _petshop = MutableLiveData<Petshop>()
    val petshop: LiveData<Petshop> = _petshop
  
    private var _favorite = MutableLiveData<Unit>()
    val favorite: LiveData<Unit> = _favorite

    private val apiPetshop by lazy {
        Rest.getInstance().create(PetshopService::class.java)
    }

    private val apiServico by lazy {
        Rest.getInstance().create(ServicoService::class.java)
    }
    
    private val apiFavorito by lazy {
        Rest.getInstance().create(FavoritoService::class.java)
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
    
    fun isFavoritado(idCliente: Int, idPetshop: Int) = viewModelScope.launch(Dispatchers.IO) {
        apiFavorito.isFavoritado(idCliente, idPetshop).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.body() == true){
                    _state.postValue(PetshopDetailViewHolder.Filled())
                    Log.i("PetshopDetailViewModelTrue", response.body().toString())
                } else{
                    _state.postValue(PetshopDetailViewHolder.Empty())
                    Log.i("PetshopDetailViewModelFalse", response.body().toString())

                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.e("PetshopDetailViewModel", "$t")
            }

        })
    }

    fun requestPostFavorito(idCliente: Int, idPetshop: Int) = viewModelScope.launch(Dispatchers.IO) {
        apiFavorito.postFavorito(idCliente, idPetshop).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                _favorite.postValue(Unit)
                _state.postValue(PetshopDetailViewHolder.Filled())
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.e("PetshopDetailViewModel", "$t")
            }

        })
    }

    fun requestDeleteFavorito(idCliente: Int, idPetshop: Int) = viewModelScope.launch(Dispatchers.IO) {
        apiFavorito.deleteFavorito(idCliente, idPetshop).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                _favorite.postValue(Unit)
                _state.postValue(PetshopDetailViewHolder.Empty())
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.e("PetshopDetailViewModel", "$t")
            }

        })
    }
}