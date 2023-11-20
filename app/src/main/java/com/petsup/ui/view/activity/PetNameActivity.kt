package com.petsup.ui.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.petsup.databinding.ActivityPetNameBinding
import com.petsup.ui.viewmodel.PetAddViewModel

class PetNameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPetNameBinding
    private val viewModel = PetAddViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.confirmButton.setOnClickListener {
            viewModel.postPetStack(binding.nameEditText.text.toString())

            val intent = Intent(this, BottomMenuActivity::class.java)
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
        viewModel.popFromStack()
        this.finish()
    }
}