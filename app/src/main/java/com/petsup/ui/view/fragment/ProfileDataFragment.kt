package com.petsup.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.petsup.databinding.FragmentProfileDataBinding

class ProfileDataFragment : Fragment(){
    lateinit var binding: FragmentProfileDataBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileDataBinding.inflate(inflater, container, false)
        return binding.root
    }
}