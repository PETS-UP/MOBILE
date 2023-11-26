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

    fun postFavorito(idCliente: Int, idPetshop: Int) {
        var request: Response<Unit>
        viewModelScope.launch {
            request = requestPostFavorito(idCliente, idPetshop)
            if (request.isSuccessful) {
                Log.i("Cool", "Funcionou? Olha o banco")
            }
        }
    }

    private suspend fun requestPostFavorito(
        idCliente: Int,
        idPetshop: Int
    ): Response<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val request = api.postFavorito(idCliente, idPetshop)
                val response = request.await()
                Response.success(response)
            } catch (e: HttpException) {
                Response.error(e.code(), e.response()?.errorBody())
            }
        }
    }
}