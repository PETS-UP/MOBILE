package com.petsup.ui.view.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.petsup.databinding.ActivityPetshopDetailBinding
import com.petsup.models.petshop.Petshop
import com.petsup.models.servico.ServicoResposta
import com.petsup.ui.model.PetshopDetailViewHolder
import com.petsup.ui.`object`.FormatterObject
import com.petsup.ui.view.adapter.ServicesAdapter
import com.petsup.ui.viewmodel.PetshopDetailViewModel

class PetshopDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPetshopDetailBinding
    private val viewModel = PetshopDetailViewModel()
    private lateinit var petshop: Petshop
    private var idCliente = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetshopDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        petshop = intent.getSerializableExtra("petshop") as Petshop

        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        idCliente = sharedPref.getInt("idCliente", 0)

        setObservers()
        getServicos(petshop.id)
        isFavoritado(idCliente, petshop.id)
        setOnClick()
        setContent()
    }

    private fun initRecyclerView(services: List<ServicoResposta>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(baseContext)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = ServicesAdapter(services, petshop)
    }

    private fun setObservers() {
        viewModel.serviceList.observe(this@PetshopDetailActivity, Observer {
            initRecyclerView(it)
        })

        viewModel.state.observe(this@PetshopDetailActivity) {
            when (it) {
                is PetshopDetailViewHolder.Filled -> {
                    binding.favoriteButtonFilled.isVisible = true
                    binding.favoriteButton.isVisible = false
                }

                is PetshopDetailViewHolder.Empty -> {
                    binding.favoriteButtonFilled.isVisible = false
                    binding.favoriteButton.isVisible = true
                }
            }
        }
    }

    private fun setOnClick() = with(binding) {
        favoriteButton.setOnClickListener {
            postFavorito(idCliente, petshop.id)
        }
        favoriteButtonFilled.setOnClickListener {
            deleteFavorito(idCliente, petshop.id)
        }
        arrowBack.setOnClickListener {
            finish()
        }
    }

    private fun setContent() = with(binding) {
        Glide.with(this@PetshopDetailActivity).load(petshop.imagemPerfil)
            .apply(RequestOptions.bitmapTransform(CircleCrop())).into(petshopIcon)
        petshopName.text = petshop.nome
        gradeTextView.text = FormatterObject.formatGrade(petshop.nota)
        petshopInfo.text = "${petshop.rua}, ${petshop.numero}\nContato - ${
            FormatterObject.formatPhoneNumber(petshop.telefone)
        }"
        petshopStatus.text = FormatterObject.formatStatus(petshop.isOpen)
    }

    private fun getServicos(idPetshop: Int) = viewModel.getServices(idPetshop)

    private fun postFavorito(idCliente: Int, idPetshop: Int) =
        viewModel.requestPostFavorito(idCliente, idPetshop)

    private fun deleteFavorito(idCliente: Int, idPetshop: Int) =
        viewModel.requestDeleteFavorito(idCliente, idPetshop)

    private fun isFavoritado(idCliente: Int, idPetshop: Int) =
        viewModel.isFavoritado(idCliente, idPetshop)
}