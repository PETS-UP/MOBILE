package com.petsup.ui.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.petsup.R
import com.petsup.models.servico.ServicoResposta
import com.petsup.ui.view.activity.BookingConfirmationActivity
import com.petsup.ui.model.ServiceViewHolder

class ServicesAdapter(private val services: List<ServicoResposta>) : RecyclerView.Adapter<ServiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_service_item, parent, false)
        return ServiceViewHolder(itemView)
    }

    override fun getItemCount() = services.size

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = services[position]

        holder.cardTitle.text = service.nome
            .lowercase()
            .split("_")
            .joinToString(" ") { it.replaceFirstChar { it.uppercase() } }

        holder.cardPrice.text = "R$ " + service.preco.toString()
        holder.cardDescription.text = service.descricao

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, BookingConfirmationActivity::class.java)
            intent.putExtra("servico", service)
        }
    }
}