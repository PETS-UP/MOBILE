package com.petsup.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petsup.api.Rest
import com.petsup.models.AgendamentoResposta
import com.petsup.services.AgendamentoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.create

class BookingListViewModel : ViewModel() {
    private var _bookingList = MutableLiveData<List<AgendamentoResposta>>()
    val bookingList: LiveData<List<AgendamentoResposta>> = _bookingList

    fun getAgendamentos(idCliente : Int) = viewModelScope.launch(Dispatchers.IO){
        _bookingList.postValue(Rest.getInstance().create<AgendamentoService>().getAgendamentosCliente(idCliente).execute().body())
    }
}