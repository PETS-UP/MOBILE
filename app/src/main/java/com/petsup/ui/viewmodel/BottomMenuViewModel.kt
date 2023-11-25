package com.petsup.ui.viewmodel

import android.content.Context
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
import retrofit2.create

class BottomMenuViewModel(private val context: Context) : ViewModel() {
    private var _cliente = MutableLiveData<ClienteDetalhes>()
    val cliente: LiveData<ClienteDetalhes> = _cliente

    fun getUserById(idCliente: Int) = viewModelScope.launch(Dispatchers.IO) {
        _cliente.postValue(
            Rest.getInstance().create<ClienteService>().getClienteById(idCliente)
                .execute().body()
        )
    }
}