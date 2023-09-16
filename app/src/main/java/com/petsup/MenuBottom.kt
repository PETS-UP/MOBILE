package com.petsup

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.petsup.databinding.BottomMenuBinding

class MenuBottom : AppCompatActivity() {

    private lateinit var binding: BottomMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BottomMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun navigateToHome(view: View) {
        val homeButton: View = findViewById(R.id.home_button)
        homeButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    fun navigateToMyPets(view: View) {
        val myPetsButton: View = findViewById(R.id.my_pets_button)
        myPetsButton.setOnClickListener {
            val intent = Intent(this, PetListActivity::class.java)
            startActivity(intent)
        }
    }

    fun navigateToBookingList(view: View) {
        val bookingListButton: View = findViewById(R.id.calendar_button)
        bookingListButton.setOnClickListener {
            val intent = Intent(this, BookingListActivity::class.java)
            startActivity(intent)
        }
    }

    fun navigateToProfile(view: View) {
        val profileButton: View = findViewById(R.id.profile_button)
        profileButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}