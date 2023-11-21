package com.petsup.ui.view.activity

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

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding


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
                    val intent = Intent(baseContext, BottomMenuActivity::class.java)
                    intent.putExtra("email", email)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@LoginActivity, "Login success!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@LoginActivity, "Login failed!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}