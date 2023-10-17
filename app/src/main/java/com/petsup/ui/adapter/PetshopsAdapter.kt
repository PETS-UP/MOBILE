package com.petsup.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.petsup.R
import com.petsup.models.petshop.Petshop
import com.petsup.ui.activity.PetshopDetailActivity

class PetshopsAdapter(private val petshops: List<Petshop>) : RecyclerView.Adapter<PetshopsAdapter.PetshopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetshopViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_petshop_item, parent, false)
        return PetshopViewHolder(itemView)
    }

    override fun getItemCount() = petshops.size

    override fun onBindViewHolder(holder: PetshopViewHolder, position: Int) {
        val petshop = petshops[position]

        holder.cardTitle.text = petshop.nome
        holder.cardLocation.text = "${petshop.bairro}, ${petshop.cidade}"
        holder.gradeTextView.text = "4.5"

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, PetshopDetailActivity::class.java)
            intent.putExtra("petshop", petshop)
            it.context.startActivity(intent)
        }
    }

    class PetshopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val petshopIcon: ImageView = itemView.findViewById(R.id.pet_shop_icon)
        val cardTitle: TextView = itemView.findViewById(R.id.card_title)
        val cardLocation: TextView = itemView.findViewById(R.id.card_location)
        val cardStatus: TextView = itemView.findViewById(R.id.card_status)
        val gradeTextView: TextView = itemView.findViewById(R.id.grade_text_view)
    }
}