package com.petsup.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.petsup.R
import com.petsup.databinding.ActivityBottomMenuBinding
import com.petsup.fragment.BookingListFragment
import com.petsup.fragment.HomeFragment
import com.petsup.fragment.PetListFragment
import com.petsup.fragment.ProfileFragment

class BottomMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomMenuBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomMenu, navController)
    }

//    fun navigateToHome(view: View) {
//        val homeButton: View = findViewById(R.id.home_nav)
//        homeButton.setOnClickListener {
//            val intent = Intent(this, HomeFragment::class.java)
//            startActivity(intent)
//        }
//    }
//
//    fun navigateToMyPets(view: View) {
//        val myPetsButton: View = findViewById(R.id.my_pets_nav)
//        myPetsButton.setOnClickListener {
//            val intent = Intent(this, PetListFragment::class.java)
//            startActivity(intent)
//        }
//    }
//
//    fun navigateToBookingList(view: View) {
//        val bookingListButton: View = findViewById(R.id.calendar_nav)
//        bookingListButton.setOnClickListener {
//            val intent = Intent(this, BookingListFragment::class.java)
//            startActivity(intent)
//        }
//    }
//
//    fun navigateToProfile(view: View) {
//        val profileButton: View = findViewById(R.id.profile_nav)
//        profileButton.setOnClickListener {
//            val intent = Intent(this, ProfileFragment::class.java)
//            startActivity(intent)
//        }
//    }
}