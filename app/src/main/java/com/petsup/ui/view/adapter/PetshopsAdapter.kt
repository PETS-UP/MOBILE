package com.petsup.ui.view.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.petsup.R
import com.petsup.models.petshop.Petshop
import com.petsup.models.petshop.PetshopExibicao
import com.petsup.ui.view.activity.PetshopDetailActivity
import com.petsup.ui.model.PetshopViewHolder
import com.petsup.ui.`object`.FormatterObject
import com.petsup.ui.view.activity.BookingConfirmationActivity
import com.petsup.ui.viewmodel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.Normalizer.Form

class PetshopsAdapter(private val petshops: List<PetshopExibicao>, private val context: Context, val fn: ((Int) -> Unit)) : RecyclerView.Adapter<PetshopViewHolder>() {

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
        holder.cardStatus.text = FormatterObject.formatStatus(petshop.isOpen ?: false)
        holder.cardStatus.setTextColor(FormatterObject.formatStatusColor(petshop.isOpen ?: false))
        holder.gradeTextView.text = FormatterObject.formatGrade(petshop.nota ?: 0.0)

        holder.itemView.setOnClickListener {
            fn(petshop.id!!)
        }
    }
}