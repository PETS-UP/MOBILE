package com.petsup.ui.model

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.petsup.R

class PetSelectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val petButton: Button = itemView.findViewById(R.id.pet_button)
}