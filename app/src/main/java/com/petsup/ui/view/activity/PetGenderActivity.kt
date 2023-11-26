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

        setObservers()
        setupOnClick()
    }

    private fun setObservers() = with(viewModel) {
        step.observe(this@PetGenderActivity) {
            val intent = Intent(this@PetGenderActivity, PetNameActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupOnClick() = with(binding) {
        maleCardView.setOnClickListener {
            viewModel.postPetStack("M")
        }

        femaleCardView.setOnClickListener {
            viewModel.postPetStack("F")
        }

        arrowBack.setOnClickListener {
            viewModel.popFromStack()
            this@PetGenderActivity.finish()
        }
    }
}