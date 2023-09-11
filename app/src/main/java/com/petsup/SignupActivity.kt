package com.petsup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
    }

    fun signup(view: View) {
        val signUpButton: Button = findViewById(R.id.signup_button)
        signUpButton.setOnClickListener {
            ValidationObject.nameValidation(findViewById(R.id.name_edit_text))
        }
    }

    fun backToMain(view: View) {
        val arrowBack: ImageView = findViewById(R.id.arrow_back)
        arrowBack.setOnClickListener {
            this.finish()
        }
    }


}