package com.petsup.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.petsup.R
import com.petsup.databinding.FragmentProfileBinding
import com.petsup.ui.view.activity.BottomMenuActivity
import com.petsup.ui.view.activity.ProfileDataActivity

class ProfileFragment : Fragment() {
    lateinit var binding : FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClick()
    }
    private fun setOnClick() = with(binding){
        editButton.setOnClickListener{
//            it.findNavController().navigate(R.id.action_profile_nav_to_profile_data_nav)
            val intent = Intent(context, ProfileDataActivity::class.java)
            startActivity(intent)
        }

    }
}