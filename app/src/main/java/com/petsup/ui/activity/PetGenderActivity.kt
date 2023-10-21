package com.petsup.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.petsup.databinding.ActivityPetGenderBinding

class PetGenderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPetGenderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetGenderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.continueButton.setOnClickListener {
            val intent = Intent(this, PetNameActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        binding.returnButton.setOnClickListener {
            back()
        }

        binding.arrowBack.setOnClickListener {
            back()
        }
    }

    private fun back() {
        val intent = Intent(this, PetSpeciesActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}