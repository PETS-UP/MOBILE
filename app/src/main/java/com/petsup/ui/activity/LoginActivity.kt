package com.petsup.ui.activity

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
            val intent = Intent(this, BottomMenuActivity::class.java)
            tryLogin(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
            startActivity(intent)
            this.finish()
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
                    Toast.makeText(this@LoginActivity, "Login success!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@LoginActivity, "Login failed!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

//    private fun tryLogin() {
//        val loginRequest = ClienteLogin(
//            "aluno@sptech.school.com", "1Sptechaluno@"
//        )
//        Rest.getInstance()
//            .create(ClienteService::class.java)
//            .login(loginRequest)
//            .enqueue(object : Callback<ClienteToken> {
//                override fun onResponse(
//                    call: Call<ClienteToken>,
//                    response: Response<ClienteToken>
//                ) {
//                    if (response.isSuccessful) {
//                        Toast.makeText(
//                            baseContext,
//                            response.body()?.token,
//                            Toast.LENGTH_LONG
//                        ).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<ClienteToken>, t: Throwable) {
//                    println(t)
//                    Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
//                }
//
//            })
//    }
}