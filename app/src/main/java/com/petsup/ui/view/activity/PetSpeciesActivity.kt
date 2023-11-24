package com.petsup.ui.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.petsup.databinding.ActivityPetSpeciesBinding
import com.petsup.ui.viewmodel.PetAddViewModel

class PetSpeciesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPetSpeciesBinding
    private val viewModel = PetAddViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetSpeciesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObservers()
        setupOnClick()
    }

    private fun setObservers() = with(viewModel) {
        step.observe(this@PetSpeciesActivity) {
            val intent = Intent(this@PetSpeciesActivity, PetGenderActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupOnClick() = with(binding) {
        dogCardView.setOnClickListener {
            viewModel.postPetStack("CACHORRO")
        }

        catCardView.setOnClickListener {
            viewModel.postPetStack("GATO")
        }

        rabbitCardView.setOnClickListener {
            viewModel.postPetStack("COELHO")
        }

        rodentCardView.setOnClickListener {
            viewModel.postPetStack("ROEDOR")
        }

        arrowBack.setOnClickListener {
            this@PetSpeciesActivity.finish()
        }
    }
}