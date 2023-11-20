package com.petsup.ui.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.petsup.databinding.ActivityPetSelectionBinding
import com.petsup.models.pet.PetResposta
import com.petsup.models.petshop.Petshop
import com.petsup.models.servico.ServicoResposta
import com.petsup.ui.view.adapter.PetsSelectionAdapter
import com.petsup.ui.viewmodel.PetSelectionViewModel

class PetSelectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPetSelectionBinding
    private val viewModel = PetSelectionViewModel()

    private lateinit var petshop: Petshop

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)

        petshop = intent.getSerializableExtra("petshop") as Petshop

        setObservers()
        getPets(sharedPreferences.getInt("idCliente", 0))

        binding.continueButton.setOnClickListener {

            val finalIntent = Intent(this, BookingConfirmationActivity::class.java)
//            finalIntent.putExtra()

            val intent = Intent(this, DatetimeSelectionActivity::class.java)
            intent.putExtra("petshop", petshop)
            startActivity(intent)
            this.finish()
        }

        binding.returnButton.setOnClickListener {
            val intent = Intent(this, PetshopDetailActivity::class.java)
            intent.putExtra("petshop", petshop)
            startActivity(intent)
            this.finish()
        }

        binding.arrowBack.setOnClickListener {
            val intent = Intent(this, PetshopDetailActivity::class.java)
            intent.putExtra("petshop", petshop)
            startActivity(intent)
            this.finish()
        }
    }

    private fun initRecyclerView(pets: List<PetResposta>) {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(baseContext, 2)
        }
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = PetsSelectionAdapter(pets, this)
    }

    private fun setObservers() {
        viewModel.petList.observe(this, Observer {
            initRecyclerView(it)
        })
    }

    private fun getPets(idCliente: Int) = viewModel.getPets(idCliente)
}