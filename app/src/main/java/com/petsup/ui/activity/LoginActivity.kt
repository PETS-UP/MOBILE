package com.petsup.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.petsup.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val intent = Intent(this, BottomMenuActivity::class.java)
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

        fun tryLogin(username: String, password: String ){
            val loginRequest = LoginRequest(username, password)
            Rest.getInstance()
                .create(DonoPetService::class.java)
                .login(loginRequest)
                .enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {
                            //SharedPreferences
                            val prefs =
                                getSharedPreferences("AUTH", AppCompatActivity.MODE_PRIVATE)
                            val editor = prefs.edit()
                            editor.putString("TOKEN", response.body().toString())
                            editor.apply()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        println(t)
                        Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                    }
                }
                )
        }
    }
}