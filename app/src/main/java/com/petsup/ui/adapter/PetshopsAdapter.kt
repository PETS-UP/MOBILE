package com.petsup.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.petsup.R
import com.petsup.models.Petshop

class PetshopsAdapter(private val petshops: List<Petshop>) : RecyclerView.Adapter<PetshopsAdapter.PetshopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetshopViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.petshop_item_adapter, parent, false)
        return PetshopViewHolder(itemView)
    }

    override fun getItemCount() = petshops.size

    override fun onBindViewHolder(holder: PetshopViewHolder, position: Int) {
        val petshop = petshops[position]

        holder.cardTitle.text = petshop.nome
        holder.cardLocation.text = "${petshop.bairro}, ${petshop.cidade}"
        holder.gradeTextView.text = "4.5"
    }

    class PetshopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val petshopIcon: ImageView = itemView.findViewById(R.id.pet_shop_icon)
        val cardTitle: TextView = itemView.findViewById(R.id.card_title)
        val cardLocation: TextView = itemView.findViewById(R.id.card_location)
        val gradeTextView: TextView = itemView.findViewById(R.id.grade_text_view)
    }
}