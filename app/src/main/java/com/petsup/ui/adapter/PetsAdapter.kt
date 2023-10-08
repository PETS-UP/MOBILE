package com.petsup.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.petsup.R
import com.petsup.models.Pet
import com.petsup.services.PetService

class PetsAdapter(private val pets: List<Pet>) : RecyclerView.Adapter<PetsAdapter.PetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_pet_item, parent, false)
        return PetViewHolder(itemView)
    }

    override fun getItemCount() = pets.size

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val pet = pets[position]

        holder.petName.text = pet.nome
        holder.petInfo.text = "${pet.especie}, ${pet.sexo}"

        holder.deleteButton.setOnClickListener {
            TODO()
        }
    }

    class PetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val petName: TextView = itemView.findViewById(R.id.pet_name)
        val petInfo: TextView = itemView.findViewById(R.id.pet_info)
        val deleteButton: ImageView = itemView.findViewById(R.id.delete_button)
    }
}