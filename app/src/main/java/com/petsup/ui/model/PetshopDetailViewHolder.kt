package com.petsup.ui.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.petsup.R

class PetshopDetailViewHolder(itemView: View) : ViewHolder(itemView) {
    val petshopIcon: ImageView = itemView.findViewById(R.id.petshop_icon)
    val petshopName: TextView = itemView.findViewById(R.id.petshop_name)
    val petshopGrade: TextView = itemView.findViewById(R.id.grade_text_view)
    val petshopInfo: TextView = itemView.findViewById(R.id.petshop_info)
    val petshopStatus: TextView = itemView.findViewById(R.id.petshop_status)
    val petshopDescription: TextView = itemView.findViewById(R.id.petshop_description)
}
