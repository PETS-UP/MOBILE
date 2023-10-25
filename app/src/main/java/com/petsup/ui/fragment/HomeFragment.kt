package com.petsup.ui.fragment

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.petsup.databinding.FragmentHomeBinding
import com.petsup.models.petshop.Petshop
import com.petsup.ui.adapter.PetshopsAdapter
import com.petsup.ui.viewmodel.HomeViewModel

class HomeFragment (application: Application): Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel = HomeViewModel(application)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
        getPetshops()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun setObservers() {
        viewModel.petshopList.observe(viewLifecycleOwner) {
            initRecyclerView(it)
        }
    }

    private fun initRecyclerView(petshops: List<Petshop>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = PetshopsAdapter(petshops)
    }

    private fun getPetshops() = viewModel.getPetshops()
}