package com.petsup

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MenuBottom : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_menu)
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
}