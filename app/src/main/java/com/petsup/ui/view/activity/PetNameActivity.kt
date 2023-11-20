package com.petsup.ui.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.petsup.databinding.ActivityPetNameBinding

class PetNameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPetNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.confirmButton.setOnClickListener {
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
        val intent = Intent(this, PetGenderActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}