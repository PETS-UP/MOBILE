package com.petsup.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.petsup.ui.`object`.ValidationObject
import com.petsup.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            ValidationObject.nameValidation(binding.nameEditText)
            ValidationObject.emailValidation(binding.emailEditText)
            ValidationObject.passwordValidation(binding.passwordEditText)
            ValidationObject.confirmPasswordValidation(
                binding.passwordEditText,
                binding.passwordConfirmationEditText
            )

            if (ValidationObject.emailValidation(binding.emailEditText) &&
                ValidationObject.nameValidation(binding.nameEditText) &&
                ValidationObject.passwordValidation(binding.passwordEditText) &&
                ValidationObject.confirmPasswordValidation(
                    binding.passwordEditText,
                    binding.passwordConfirmationEditText
                )
            ){
                startActivity(intent)
                this.finish()
            }
        }

        binding.redirectToLoginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        binding.arrowBack.setOnClickListener {
            this.finish()
        }
    }
}