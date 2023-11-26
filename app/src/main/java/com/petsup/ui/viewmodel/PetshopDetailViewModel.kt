package com.petsup.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petsup.api.Rest
import com.petsup.models.cliente.ClienteDetalhes
import com.petsup.models.servico.ServicoResposta
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

    private var _favorite = MutableLiveData<Unit>()
    val favorite: LiveData<Unit> = _favorite

    private val api by lazy {
        Rest.getInstance().create(FavoritoService::class.java)
    }

    fun getServices(idPetshop: Int) = viewModelScope.launch(Dispatchers.IO) {
        _serviceList.postValue(
            Rest.getInstance().create<ServicoService>().getServicosByIdPetshop(idPetshop).execute()
                .body()
        )
    }

    fun isFavoritado(idCliente: Int, idPetshop: Int) = viewModelScope.launch(Dispatchers.IO) {
        api.isFavoritado(idCliente, idPetshop).enqueue(object : Callback<Boolean> {
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
        api.postFavorito(idCliente, idPetshop).enqueue(object : Callback<Unit> {
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
        api.deleteFavorito(idCliente, idPetshop).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                _favorite.postValue(Unit)
                _state.postValue(PetshopDetailViewHolder.Empty())
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.e("PetshopDetailViewModel", "$t")
            }

        })
    }

    private suspend fun requestIsFavoritado(
        idCliente: Int,
        idPetshop: Int
    ): Response<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                val request = api.isFavoritado(idCliente, idPetshop)
                val response = request.await()
                Response.success(response)
            } catch (e: HttpException) {
                Response.error(e.code(), e.response()?.errorBody())
            }
        }
    }
}