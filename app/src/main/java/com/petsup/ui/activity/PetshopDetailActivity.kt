package com.petsup.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.petsup.databinding.ActivityPetshopDetailBinding
import com.petsup.models.petshop.Petshop

class PetshopDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPetshopDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetshopDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val petshop = intent.getSerializableExtra("petshop") as Petshop?
    }
}