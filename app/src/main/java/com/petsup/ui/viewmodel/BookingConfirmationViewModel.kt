package com.petsup.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.petsup.api.Rest
import com.petsup.services.AgendamentoService
import com.petsup.ui.model.BookingConfirmationViewHolder

class BookingConfirmationViewModel : ViewModel() {

    private var _state = MutableLiveData<BookingConfirmationViewHolder>()
    val state: LiveData<BookingConfirmationViewHolder> = _state

    private val api by lazy {
        Rest.getInstance().create(AgendamentoService::class.java)
    }

    fun updateViewStateToContent() {
        _state.value = BookingConfirmationViewHolder.Content()
    }
}