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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.await

class ProfileDataViewModel : ViewModel() {
    private var _profileData = MutableLiveData<List<ServicoResposta>>()
    val profileData: LiveData<List<ServicoResposta>> = _profileData
    lateinit var clienteDetalhes: ClienteDetalhes

    private val api by lazy {
        Rest.getInstance().create(ClienteService::class.java)
    }

    fun getUserById(idCliente: Int): ClienteDetalhes {
        var request: Response<ClienteDetalhes>
//        api.getClienteById(idCliente)
//                 .enqueue(object : Callback<ClienteDetalhes> {
//                        override fun onResponse(
//                            call: Call<ClienteDetalhes>,
//                            response: Response<ClienteDetalhes>
//                        ) {
//                            if (response.isSuccessful) {
//                                clienteDetalhes = response.body()!!
//                            }
//                        }
//                        override fun onFailure(call: Call<ClienteDetalhes>, t: Throwable) {
//                            Log.d("Request Error", "Erro na requisição de ID $t")
//                        }
//
//                    })
        viewModelScope.launch {
            request = requestGetUser(idCliente)
            if (request.isSuccessful){

            }
        }
        return request.body()!!
    }

    private suspend fun requestGetUser(idCliente: Int): Response<ClienteDetalhes> {
        return withContext(Dispatchers.IO) {
            try {
                val request = api.getClienteById(idCliente)
                val response = request.await()
                Response.success(response)
            } catch (e: HttpException) {
                Response.error(e.code(), e.response()?.errorBody())
            }
        }
    }
}