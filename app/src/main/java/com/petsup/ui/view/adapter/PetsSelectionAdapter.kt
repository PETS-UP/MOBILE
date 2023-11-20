package com.petsup.ui.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.petsup.R
import com.petsup.models.pet.PetResposta
import com.petsup.ui.model.PetSelectionViewHolder
import com.petsup.ui.view.activity.BookingConfirmationActivity
import com.petsup.ui.view.activity.DatetimeSelectionActivity

class PetsSelectionAdapter(private val pets: List<PetResposta>, private val context: Context) : RecyclerView.Adapter<PetSelectionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetSelectionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_pet_selection_item, parent, false)
        return PetSelectionViewHolder(itemView)
    }

    override fun getItemCount() = pets.size

    override fun onBindViewHolder(holder: PetSelectionViewHolder, position: Int) {
        val pet = pets[position]

        holder.petButton.text = pet.nome

        holder.itemView.setOnClickListener {
            val sharedPref = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()

            editor.putInt("idPet", pet.id)
            editor.putString("nomePet", pet.nome)
            editor.putString("sexoPet", pet.sexo)
            editor.putString("especiePet", pet.especie)

            editor.apply()
        }
    }

}