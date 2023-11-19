package com.petsup.ui.viewmodel

import android.content.Context
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
import retrofit2.create

class BottomMenuViewModel(private val context: Context) : ViewModel() {
    private var _cliente = MutableLiveData<ClienteDetalhes>()
    val cliente: LiveData<ClienteDetalhes> = _cliente

    private val api by lazy {
        Rest.getInstance().create(ClienteService::class.java)
    }

    fun getUserById(idCliente: Int) = viewModelScope.launch(Dispatchers.IO) {
        _cliente.postValue(
            Rest.getInstance().create<ClienteService>().getClienteById(idCliente)
                .execute().body()
        )
    }

    fun getUserByEmail(email: String): LiveData<ClienteDetalhes> {
        var clienteDetalhes: ClienteDetalhes
        viewModelScope.launch {
            val request = api.getUserByEmail(email)
                .enqueue(object : Callback<ClienteDetalhes> {
                    override fun onResponse(
                        call: Call<ClienteDetalhes>,
                        response: Response<ClienteDetalhes>
                    ) {
                        if (response.isSuccessful) {
                            clienteDetalhes = response.body()!!
                            clienteDetalhes?.let {
                                saveToSharedPreferences(it)
                                _cliente.postValue(it)
                            }
//
//                            saveToSharedPreferences(clienteDetalhes)
//                            _cliente.postValue(clienteDetalhes)
                        }
                    }

                    override fun onFailure(call: Call<ClienteDetalhes>, t: Throwable) {
                        print("erro API")
                    }

                })
        }
        return _cliente
    }

    private fun saveToSharedPreferences(clienteDetalhes: ClienteDetalhes) {
        val sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putInt("idCliente", clienteDetalhes.id)
        editor.putString("nomeCliente", clienteDetalhes.nome)
        editor.putString("emailCliente", clienteDetalhes.email)

        editor.apply()
    }
}