package com.petsup.ui.view.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.petsup.R
import com.petsup.models.petshop.Petshop
import com.petsup.ui.model.PetshopViewHolder
import com.petsup.ui.`object`.FormatterObject
import com.petsup.ui.view.activity.PetshopDetailActivity
import java.text.Normalizer.Form

class PetshopsAdapter(private val petshops: List<Petshop>, private val context: Context) : RecyclerView.Adapter<PetshopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetshopViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_petshop_item, parent, false)
        return PetshopViewHolder(itemView)
    }

    override fun getItemCount() = petshops.size

    override fun onBindViewHolder(holder: PetshopViewHolder, position: Int) {
        val petshop = petshops[position]

        Glide.with(context).load(petshop.imagemPerfil).into(holder.petshopIcon)
        holder.cardTitle.text = petshop.nome
        holder.cardLocation.text = "${petshop.rua}, ${petshop.numero}"
        holder.cardStatus.text = FormatterObject.formatStatus(petshop.isOpen)
        holder.cardStatus.setTextColor(FormatterObject.formatStatusColor(petshop.isOpen))
        holder.gradeTextView.text = FormatterObject.formatGrade(petshop.nota)

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, PetshopDetailActivity::class.java)
            intent.putExtra("petshop", petshop)
            it.context.startActivity(intent)
        }
    }
}