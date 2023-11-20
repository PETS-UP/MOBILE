package com.petsup.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.petsup.api.Rest
import com.petsup.services.AgendamentoService
import com.petsup.ui.model.BookingConfirmationViewHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookingConfirmationViewModel : ViewModel() {

    private var _state = MutableLiveData<BookingConfirmationViewHolder>()
    val state: LiveData<BookingConfirmationViewHolder> = _state

    private val api by lazy {
        Rest.getInstance().create(AgendamentoService::class.java)
    }

    fun postAgendamento(dataHora: String, idCliente: Int, idPetshop: Int, idPet: Int, idServico: Int) {
        val request = api.postAgendamento(dataHora, idCliente, idPetshop, idPet, idServico)

        request.enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    _state.value = BookingConfirmationViewHolder.Success()
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                _state.value = BookingConfirmationViewHolder.Error()
            }

        })
    }

    fun updateViewStateToContent() {
        _state.value = BookingConfirmationViewHolder.Content()
    }
}