package com.petsup.ui.viewmodel

import android.content.Context
import android.util.Log
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

class LoginViewModel(private val context: Context) : ViewModel() {

    private val api by lazy {
        Rest.getInstance().create(ClienteService::class.java)
    }

    fun getUserByEmail(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            api.getUserByEmail(email)
                .enqueue(object : Callback<ClienteDetalhes> {
                    override fun onResponse(
                        call: Call<ClienteDetalhes>,
                        response: Response<ClienteDetalhes>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                saveToSharedPreferences(it)
                                Log.i("PERFIL", it.toString())
                            }
                        }
                    }
                    override fun onFailure(call: Call<ClienteDetalhes>, t: Throwable) {
                        Log.d("BBBBBBBBBBB", "Erro na requisição de email $t")
                    }

                })
        }
    }

    private fun saveToSharedPreferences(clienteDetalhes: ClienteDetalhes) {
        val sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putInt("idCliente", clienteDetalhes.id)
        editor.putString("nomeCliente", clienteDetalhes.nome)
        editor.putString("emailCliente", clienteDetalhes.email)
        editor.putString("imagemPerfilCliente", clienteDetalhes.imagemPerfil)

        editor.apply()
    }
}