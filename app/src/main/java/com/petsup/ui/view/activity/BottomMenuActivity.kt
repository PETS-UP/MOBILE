package com.petsup.ui.view.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.gson.Gson
import com.petsup.R
import com.petsup.databinding.ActivityBottomMenuBinding
import com.petsup.models.cliente.ClienteDetalhes
import com.petsup.ui.viewmodel.BottomMenuViewModel


class BottomMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomMenuBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: BottomMenuViewModel
    private lateinit var clienteDetalhes: ClienteDetalhes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = BottomMenuViewModel(this)

        val email = intent.getStringExtra("email")
        getUserByEmail(email!!)
        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomMenu, navController)
    }

    private fun getUserByEmail(email: String) = viewModel.getUserByEmail(email)

}