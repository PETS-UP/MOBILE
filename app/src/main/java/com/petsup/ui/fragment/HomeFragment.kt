package com.petsup.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.petsup.databinding.FragmentHomeBinding
import com.petsup.models.Petshop
import com.petsup.services.PetshopService
import com.petsup.ui.adapter.PetshopsAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel = HomeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setObservers()
        getPetshops()
    }

    private fun setObservers() {
        viewModel.petshopList.observe(this) {
            initRecyclerView(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun initRecyclerView(petshops: List<Petshop>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = PetshopsAdapter(petshops)
    }

    private fun getPetshops() = viewModel.getPetshops()
}