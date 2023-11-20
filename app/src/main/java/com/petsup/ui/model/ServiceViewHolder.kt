package com.petsup.ui.model

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.petsup.R

class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val cardTitle: TextView = itemView.findViewById(R.id.card_title)
    val cardPrice: TextView = itemView.findViewById(R.id.card_price)
    val cardDescription: TextView = itemView.findViewById(R.id.card_description)
}