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
    }

    fun confirm(view: View) {
        val confirmButton: Button = findViewById(R.id.confirm_button)
        confirmButton.setOnClickListener {
            val isValid = ValidationObject.petNameValidation(binding.nameEditText)
            if (isValid) {
                val intent = Intent(this, PetListFragment::class.java)
                startActivity(intent)
            }
        }
    }

    fun back(view: View) {
        val arrowBack: ImageView = findViewById(R.id.arrow_back)
        arrowBack.setOnClickListener {
            this.finish()
        }

        val returnButton: Button = findViewById(R.id.return_button)
        returnButton.setOnClickListener {
            this.finish()
        }
    }
}