package com.petsup.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.petsup.R
import com.petsup.models.servico.ServicoResposta
import com.petsup.ui.activity.PetSelectionActivity

class ServicesAdapter(private val services: List<ServicoResposta>) : RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_service_item, parent, false)
        return ServicesAdapter.ServiceViewHolder(itemView)
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
            val intent = Intent(it.context, PetSelectionActivity::class.java)
            intent.putExtra("idServico", service.id)
            intent.putExtra("servico", service.nome)
            intent.putExtra("preco", service.preco)
        }
    }

    class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardTitle: TextView = itemView.findViewById(R.id.card_title)
        val cardPrice: TextView = itemView.findViewById(R.id.card_price)
        val cardDescription: TextView = itemView.findViewById(R.id.card_description)
    }
}