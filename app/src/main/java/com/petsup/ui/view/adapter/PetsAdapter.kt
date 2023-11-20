package com.petsup.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.petsup.R
import com.petsup.models.pet.PetResposta
import com.petsup.ui.model.PetViewHolder

class PetsAdapter(private val petCadastros: List<PetResposta>) : RecyclerView.Adapter<PetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_pet_item, parent, false)
        return PetViewHolder(itemView)
    }

    override fun getItemCount() = petCadastros.size

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val pet = petCadastros[position]

        holder.petName.text = pet.nome
        holder.petInfo.text = "${pet.especie}, ${pet.sexo}"

        holder.deleteButton.setOnClickListener {
            TODO()
        }
    }
}