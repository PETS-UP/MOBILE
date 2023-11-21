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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileDataViewModel : ViewModel()  {
    private var _profileData = MutableLiveData<List<ServicoResposta>>()
    val profileData: LiveData<List<ServicoResposta>> = _profileData
    lateinit var clienteDetalhes: ClienteDetalhes

    private val api by lazy {
        Rest.getInstance().create(ClienteService::class.java)
    }

    fun getUserById(idCliente: Int): ClienteDetalhes {
        viewModelScope.launch(Dispatchers.IO) {
            api.getClienteById(idCliente)
                .enqueue(object : Callback<ClienteDetalhes> {
                    override fun onResponse(
                        call: Call<ClienteDetalhes>,
                        response: Response<ClienteDetalhes>
                    ) {
                        if (response.isSuccessful) {
                            clienteDetalhes = response.body()!!
                        }
                    }
                    override fun onFailure(call: Call<ClienteDetalhes>, t: Throwable) {
                        Log.d("Request Error", "Erro na requisição de ID $t")
                    }

                })
        }

        return clienteDetalhes
    }
}