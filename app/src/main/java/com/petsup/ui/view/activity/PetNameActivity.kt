package com.petsup.ui.view.activity

import android.content.Context
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

        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val idCliente = sharedPref.getInt("idCliente", 0)

        binding.confirmButton.setOnClickListener {
            viewModel.postPetStack(binding.nameEditText.text.toString())
            viewModel.postPet(idCliente)
            viewModel.clearStack()

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