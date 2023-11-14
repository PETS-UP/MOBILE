package com.petsup.ui.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.petsup.databinding.ActivityPetSelectionBinding
import com.petsup.models.pet.PetResposta
import com.petsup.ui.view.adapter.PetsSelectionAdapter
import com.petsup.ui.viewmodel.PetSelectionViewModel

class PetSelectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPetSelectionBinding
    private val viewModel = PetSelectionViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObservers()
        getPets()

        binding.continueButton.setOnClickListener {
            val finalIntent = Intent(this, BookingConfirmationActivity::class.java)
            finalIntent.putExtra()

            val intent = Intent(this, DatetimeSelectionActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        binding.returnButton.setOnClickListener {
            this.finish()
        }

        binding.arrowBack.setOnClickListener {
            this.finish()
        }
    }

    private fun initRecyclerView(pets: List<PetResposta>) {
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(baseContext, 2)
        }
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = PetsSelectionAdapter(pets)
    }

    private fun setObservers() {
        viewModel.petList.observe(this, Observer {
            initRecyclerView(it)
        })
    }

    private fun getPets(idCliente: Int) = viewModel.getPets(idCliente)
}