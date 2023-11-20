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

        binding.dogCardView.setOnClickListener {
            viewModel.postPetStack("cachorro")

            val intent = Intent(this, PetGenderActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        binding.catCardView.setOnClickListener {
            viewModel.postPetStack("gato")

            val intent = Intent(this, PetGenderActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        binding.rabbitCardView.setOnClickListener {
            viewModel.postPetStack("coelho")

            val intent = Intent(this, PetGenderActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        binding.rodentCardView.setOnClickListener {
            viewModel.postPetStack("roedor")

            val intent = Intent(this, PetGenderActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        binding.arrowBack.setOnClickListener {
            this.finish()
        }
    }


}