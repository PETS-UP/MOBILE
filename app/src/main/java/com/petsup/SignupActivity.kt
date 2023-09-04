package com.petsup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
    }

    fun backToMain(view: View) {
        val arrowBack: ImageView = findViewById(R.id.arrow_back)
        arrowBack.setOnClickListener {
            this.finish()
        }
    }
}