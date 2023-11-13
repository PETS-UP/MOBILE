package com.petsup.ui.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.petsup.R
import com.petsup.models.petshop.Petshop
import com.petsup.ui.view.activity.PetshopDetailActivity
import com.petsup.ui.model.PetshopViewHolder

class PetshopsAdapter(private val petshops: List<Petshop>) : RecyclerView.Adapter<PetshopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetshopViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_petshop_item, parent, false)
        return PetshopViewHolder(itemView)
    }

    override fun getItemCount() = petshops.size

    override fun onBindViewHolder(holder: PetshopViewHolder, position: Int) {
        val petshop = petshops[position]

        holder.cardTitle.text = petshop.nome
        holder.cardLocation.text = "${petshop.rua}, ${petshop.numero}"
        holder.gradeTextView.text = "4.5"

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, PetshopDetailActivity::class.java)
            intent.putExtra("petshop", petshop)
            it.context.startActivity(intent)
        }
    }
}