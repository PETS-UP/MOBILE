package com.petsup.ui.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetshopDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        petshop = intent.getSerializableExtra("petshop") as Petshop

        setObservers()

        binding.continueButton.setOnClickListener {
            val intent = Intent(this, DatetimeSelectionActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    private fun initRecyclerView(services: List<ServicoResposta>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(baseContext)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = ServicesAdapter(services)
    }

    private fun setObservers() {
        viewModel.serviceList.observe(this, Observer {
            initRecyclerView(it)
        })
    }

    fun bindViewHolder(holder: PetshopDetailViewHolder) {
        holder.petshopIcon.setImageURI(Uri.parse(petshop.imagemPerfil))
        holder.petshopName.text = petshop.nome
        holder.petshopGrade.text = String.format("%.1f", petshop.nota)
        holder.petshopInfo.text = "${petshop.rua}, ${petshop.numero}\nContato - ${FormatterObject.formatPhoneNumber(petshop.telefone)}"
        holder.petshopStatus.text = FormatterObject.formatStatus(petshop.isOpen)
    }
}