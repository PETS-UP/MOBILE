package com.petsup.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.petsup.R
import com.petsup.databinding.ActivityPetNameBinding
import com.petsup.ui.`object`.ValidationObject
import com.petsup.ui.fragment.PetListFragment

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