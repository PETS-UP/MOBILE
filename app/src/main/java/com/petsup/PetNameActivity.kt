package com.petsup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class PetNameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_name)
    }

    fun confirm(view: View) {
        val confirmButton: Button = findViewById(R.id.confirm_button)
        confirmButton.setOnClickListener {
            val isValid = ValidationObject.petNameValidation(findViewById(R.id.name_edit_text))
            if (isValid) {
                val intent = Intent(this, PetListActivity::class.java)
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