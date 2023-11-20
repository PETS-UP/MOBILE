package com.petsup.ui.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.petsup.R

class PetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val petName: TextView = itemView.findViewById(R.id.pet_name)
    val petInfo: TextView = itemView.findViewById(R.id.pet_info)
    val deleteButton: ImageView = itemView.findViewById(R.id.delete_button)
}