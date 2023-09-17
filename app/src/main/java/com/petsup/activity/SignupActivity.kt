package com.petsup.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.petsup.`object`.ValidationObject
import com.petsup.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupButton.setOnClickListener {
            ValidationObject.nameValidation(binding.nameEditText)
            ValidationObject.emailValidation(binding.emailEditText)
            ValidationObject.passwordValidation(binding.passwordEditText)
        }

        binding.redirectToLoginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.arrowBack.setOnClickListener {
            this.finish()
        }
    }
}