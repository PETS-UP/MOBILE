package com.petsup.ui.model

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.petsup.R

class BookingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val petshopName: TextView = itemView.findViewById(R.id.petshop_name)
    val serviceName: TextView = itemView.findViewById(R.id.service_name)
    val petName: TextView = itemView.findViewById(R.id.pet_name)
    val dateName: TextView = itemView.findViewById(R.id.date_name)
    val timeName: TextView = itemView.findViewById(R.id.time_name)
    val priceName: TextView = itemView.findViewById(R.id.price_name)
}