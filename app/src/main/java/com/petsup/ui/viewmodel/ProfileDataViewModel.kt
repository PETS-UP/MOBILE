package com.petsup.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petsup.api.Rest
import com.petsup.models.cliente.ClienteDetalhes
import com.petsup.services.ClienteService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileDataViewModel : ViewModel() {
    private var _profileData = MutableLiveData<ClienteDetalhes>()
    val profileData: LiveData<ClienteDetalhes> = _profileData
//    lateinit var clienteDetalhes: ClienteDetalhes

    private val api by lazy {
        Rest.getInstance().create(ClienteService::class.java)
    }

    fun getUserById(idCliente: Int){
//        var request: Response<ClienteDetalhes>
//        viewModelScope.launch {
//            request = requestGetUser(idCliente)
//            if (request.isSuccessful){
//
//            }
//        }

        viewModelScope.launch(Dispatchers.IO) {
            api.getClienteById(idCliente)
                .enqueue(object : Callback<ClienteDetalhes> {
                    override fun onResponse(
                        call: Call<ClienteDetalhes>,
                        response: Response<ClienteDetalhes>
                    ) {
                        if (response.isSuccessful) {
                            _profileData.postValue(response.body()!!)
                        }
                    }

                    override fun onFailure(call: Call<ClienteDetalhes>, t: Throwable) {
                        Log.d("Request Error", "Erro na requisição de busca de cliente $t")
                    }
                })
        }
    }

//    private suspend fun requestGetUser(idCliente: Int): Response<ClienteDetalhes> {
//        return withContext(Dispatchers.IO) {
//            try {
//                val request = api.getClienteById(idCliente)
//                val response = request.await()
//                Response.success(response)
//            } catch (e: HttpException) {
//                Response.error(e.code(), e.response()?.errorBody())
//            }
//        }
//    }
}