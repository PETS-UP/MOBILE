package com.petsup.ui.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.petsup.api.Rest
import com.petsup.databinding.ActivityLoginBinding
import com.petsup.models.cliente.ClienteLogin
import com.petsup.models.cliente.ClienteToken
import com.petsup.services.ClienteService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast
import com.petsup.ui.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel by lazy {
        LoginViewModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            tryLogin(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
        }

        binding.redirectToSignupButton.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        binding.arrowBack.setOnClickListener {
            this.finish()
        }
    }

    private fun tryLogin(email: String, senha: String){
        val retIn = Rest.getInstance().create(ClienteService::class.java)
        val signInInfo = ClienteLogin(email, senha)

        retIn.login(signInInfo).enqueue(object : Callback<ClienteToken> {
            override fun onFailure(call: Call<ClienteToken>, t: Throwable) {
                Toast.makeText(
                    this@LoginActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(call: Call<ClienteToken>, response: Response<ClienteToken>) {
                if (response.code() == 200) {
                    getUserByEmail(email)

                    val intent = Intent(baseContext, BottomMenuActivity::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@LoginActivity, "Login success!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@LoginActivity, "Login failed!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun getUserByEmail(email: String) = viewModel.getUserByEmail(email)
}