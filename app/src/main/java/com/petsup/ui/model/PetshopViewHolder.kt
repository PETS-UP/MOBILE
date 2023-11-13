package com.petsup.ui.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.petsup.R

class PetshopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val petshopIcon: ImageView = itemView.findViewById(R.id.pet_shop_icon)
    val cardTitle: TextView = itemView.findViewById(R.id.card_title)
    val cardLocation: TextView = itemView.findViewById(R.id.card_location)
    val cardStatus: TextView = itemView.findViewById(R.id.card_status)
    val gradeTextView: TextView = itemView.findViewById(R.id.grade_text_view)
}