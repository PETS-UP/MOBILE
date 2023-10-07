package com.petsup.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.petsup.R

class PetGenderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_gender)
    }

    fun next(view: View) {
        val continueButton: Button = findViewById(R.id.continue_button)
        continueButton.setOnClickListener {
            val intent = Intent(this, PetNameActivity::class.java)
            startActivity(intent)
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