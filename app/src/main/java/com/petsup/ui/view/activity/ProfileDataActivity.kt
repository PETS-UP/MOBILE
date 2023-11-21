package com.petsup.ui.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.petsup.databinding.ActivityProfileDataBinding

class ProfileDataActivity : AppCompatActivity(){
    private lateinit var binding: ActivityProfileDataBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityProfileDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.arrowBack.setOnClickListener {
            back()
        }
    }

    private fun back() {
        val intent = Intent(this, BottomMenuActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}