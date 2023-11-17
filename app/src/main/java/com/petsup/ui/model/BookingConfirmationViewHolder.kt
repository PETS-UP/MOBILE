package com.petsup.ui.model

import com.petsup.models.pet.PetResposta
import com.petsup.models.petshop.Petshop
import com.petsup.models.servico.ServicoResposta

sealed class BookingConfirmationViewHolder {
    class Content : BookingConfirmationViewHolder()
    class Success : BookingConfirmationViewHolder()
    class Error : BookingConfirmationViewHolder()
}