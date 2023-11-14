package com.petsup.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.gson.Gson
import com.petsup.R
import com.petsup.databinding.ActivityBottomMenuBinding
import com.petsup.ui.viewmodel.BottomMenuViewModel


class BottomMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomMenuBinding
    private lateinit var navController: NavController
    private val viewModel = BottomMenuViewModel()

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("email")
        if (email != null) {
           getUserByEmail(email)
        }
        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomMenu, navController)
    }

    private fun getUserByEmail(email: String) {
        val user = viewModel.getUserByEmail(email)
        val sharedPref = getSharedPreferences("application", MODE_PRIVATE)
        val gson = Gson()
        val json = gson.toJson(user)
        val editor = sharedPref.edit()
        editor.putString("SerializableUSER", json)
        editor.commit()
    }

}