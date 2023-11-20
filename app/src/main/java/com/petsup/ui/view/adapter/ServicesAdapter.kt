package com.petsup.ui.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.petsup.R
import com.petsup.models.servico.ServicoResposta
import com.petsup.ui.model.ServiceViewHolder
import com.petsup.ui.`object`.FormatterObject
import com.petsup.ui.view.activity.PetSelectionActivity

class ServicesAdapter(private val services: List<ServicoResposta>, private val context: Context) : RecyclerView.Adapter<ServiceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_service_item, parent, false)
        return ServiceViewHolder(itemView)
    }

    override fun getItemCount() = services.size

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = services[position]

        holder.cardTitle.text = FormatterObject.formatServiceName(service.nome)
        holder.cardPrice.text = service.preco
        holder.cardDescription.text = service.descricao

        holder.itemView.setOnClickListener {
            val sharedPref = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()

            editor.putInt("idServico", service.id)
            editor.putString("nomeServico", service.nome)
            editor.putString("precoServico", service.preco)
            editor.putString("descricaoServico", service.descricao)

            editor.apply()
        }
    }
}