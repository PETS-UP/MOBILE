package com.petsup.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.petsup.R
import com.petsup.models.AgendamentoResposta
import com.petsup.ui.model.BookingViewHolder
import com.petsup.ui.model.PetViewHolder

class BookingsAdapter(private val petsBooking : List<AgendamentoResposta>) : RecyclerView.Adapter<BookingViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_booking_item, parent, false)
        return BookingViewHolder(itemView)
    }

    override fun getItemCount() = petsBooking.size

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val booking = petsBooking[position]

        holder.petshopName.text = booking.nomePetshop
        holder.serviceName.text = booking.servico
        holder.petName.text = booking.nomePet
        //holder.dateName.text = booking.dataHora.
        //holder.timeName.text
        holder.priceName.text = booking.preco.toString()
    }
}