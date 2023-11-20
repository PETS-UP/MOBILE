package com.petsup.ui.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.petsup.databinding.ActivityPetGenderBinding
import com.petsup.ui.viewmodel.PetAddViewModel

class PetGenderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPetGenderBinding
    private val viewModel = PetAddViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetGenderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.maleCardView.setOnClickListener {
            viewModel.postPetStack("M")
            val intent = Intent(this, PetNameActivity::class.java)
            startActivity(intent)
        }

        binding.femaleCardView.setOnClickListener {
            viewModel.postPetStack("F")
            val intent = Intent(this, PetNameActivity::class.java)
            startActivity(intent)
        }

        binding.arrowBack.setOnClickListener {
            viewModel.popFromStack()
            this.finish()
        }
    }
}