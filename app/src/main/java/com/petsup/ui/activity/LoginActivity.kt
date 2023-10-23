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
import com.petsup.api.SessionManager

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sessionManager: SessionManager


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

    fun tryLogin(email: String, senha: String){
        val retIn = Rest.getInstance().create(ClienteService::class.java)
        val signInInfo = ClienteLogin(email, senha)
        sessionManager = SessionManager(this)

        retIn.login(signInInfo).enqueue(object : Callback<ClienteToken> {
            override fun onFailure(call: Call<ClienteToken>, t: Throwable) {
                Toast.makeText(
                    this@LoginActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(call: Call<ClienteToken>, response: Response<ClienteToken>) {
                val loginResponse = response.body()
                if (response.code() == 200 && loginResponse?.token != null) {
                    sessionManager.saveAuthToken(loginResponse.token)

                    // Agora que você tem o token, você pode usá-lo para fazer outra solicitação
                    Toast.makeText(this@LoginActivity, "Login success!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@LoginActivity, "Login failed!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

//    private fun fetchPosts() {
//
//        // Pass the token as parameter
//        Rest.getInstance().fetchPosts(token = "Bearer ${sessionManager.fetchAuthToken()}")
//            .enqueue(object : Callback<PostsResponse> {
//                override fun onFailure(call: Callback<PostsResponse>, t: Throwable) {
//                    // Error fetching posts
//                }
//
//                override fun onResponse(call: Call<PostsResponse>, response: Response<PostsResponse>) {
//                    // Handle function to display posts
//                }
//            })
//    }

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