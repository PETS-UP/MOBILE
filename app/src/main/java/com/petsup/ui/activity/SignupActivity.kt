package com.petsup.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.petsup.api.Rest
import com.petsup.ui.`object`.ValidationObject
import com.petsup.databinding.ActivitySignupBinding
import com.petsup.models.cliente.ClienteCadastro
import com.petsup.models.cliente.ClienteDetalhes
import com.petsup.services.ClienteService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            ) {
                signUp(binding.nameEditText.text.toString(),
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString())
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

    private fun signUp(nome: String, email: String, senha: String) {
        val retIn = Rest.getInstance().create(ClienteService::class.java)
        val registerInfo = ClienteCadastro(nome, email, senha)

        retIn.postCLiente(registerInfo).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>){
                if (response.isSuccessful) {
                    // A resposta da API foi bem-sucedida
                    Toast.makeText(this@SignupActivity, "Registration success!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    // A resposta da API não foi bem-sucedida. Lide com isso aqui, por exemplo,
                    // mostrando uma mensagem de erro.
                    Toast.makeText(this@SignupActivity, "Registration failed!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                // Ocorreu um erro na chamada à API. Lide com isso aqui, por exemplo,
                // mostrando uma mensagem de erro.
                Toast.makeText(
                    this@SignupActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}