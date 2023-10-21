package com.petsup.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.petsup.databinding.ActivityPetSpeciesBinding

class PetSpeciesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPetSpeciesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetSpeciesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.continueButton.setOnClickListener {
            val intent = Intent(this, PetGenderActivity::class.java)
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
}