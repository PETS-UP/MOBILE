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
        getServicos(petshop.id)

        binding.continueButton.setOnClickListener {
            val intent = Intent(this, DatetimeSelectionActivity::class.java)
            intent.putExtra("idPetshop", petshop)
            startActivity(intent)
            this.finish()
        }

        binding.arrowBack.setOnClickListener {
            this.finish()
        }

        binding.returnButton.setOnClickListener {
            this.finish()
        }

        binding.petshopIcon.setImageURI(Uri.parse(petshop.imagemPerfil))
        binding.petshopName.text = petshop.nome
        binding.gradeTextView.text = FormatterObject.formatGrade(petshop.nota)
        binding.petshopInfo.text = "${petshop.rua}, ${petshop.numero}\nContato - ${FormatterObject.formatPhoneNumber(petshop.telefone)}"
        binding.petshopStatus.text = FormatterObject.formatStatus(petshop.isOpen)
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

    private fun getServicos(idPetshop: Int) = viewModel.getServices(petshop.id)
}