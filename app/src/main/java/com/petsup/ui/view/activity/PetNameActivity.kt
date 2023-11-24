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

    private val sharedPref by lazy {
        getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    private val idCliente by lazy {
        sharedPref.getInt("idCliente", 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObservers()
        setupOnClick()
    }

    private fun setObservers() = with(viewModel) {
        step.observe(this@PetNameActivity) {
            viewModel.postPet(idCliente)
        }

        lastStep.observe(this@PetNameActivity) {
            clearStack()
            val intent = Intent(this@PetNameActivity, BottomMenuActivity::class.java)
            startActivity(intent)
            this@PetNameActivity.finish()
        }
    }

    private fun setupOnClick() = with(binding) {
        confirmButton.setOnClickListener {
            val name = nameEditText.text.toString()
            viewModel.postPetStack(name)
        }

        returnButton.setOnClickListener {
            back()
        }

        arrowBack.setOnClickListener {
            back()
        }
    }

    private fun back() {
        viewModel.popFromStack()
        this.finish()
    }
}