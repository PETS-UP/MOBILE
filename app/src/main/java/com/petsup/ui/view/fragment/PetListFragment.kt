package com.petsup.ui.view.fragment

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.petsup.R
import com.petsup.databinding.FragmentPetListBinding
import com.petsup.models.pet.PetResposta
import com.petsup.ui.model.PetListViewHolder
import com.petsup.ui.view.activity.PetSpeciesActivity
import com.petsup.ui.view.adapter.PetsAdapter
import com.petsup.ui.viewmodel.PetListViewModel


class PetListFragment : Fragment() {

    private lateinit var binding: FragmentPetListBinding
    private val viewModel = PetListViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPetListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        getPets()
    }

    private fun setObservers() {
        viewModel.petList.observe(viewLifecycleOwner) {
            if (it != null) {
                initRecyclerView(it)
            }
        }

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is PetListViewHolder.EmptyPetList -> {
                    binding.emptyPetList.isVisible = true
                    binding.petList.isVisible = false

                    binding.addPetButton.setOnClickListener {
                        val intent = Intent(requireActivity(), PetSpeciesActivity::class.java)
                        requireActivity().startActivity(intent)
                    }
                }

                is PetListViewHolder.PetList -> {
                    binding.petList.isVisible = true
                    binding.emptyPetList.isVisible = false

                    binding.addButton.setOnClickListener {
                        val intent = Intent(context, PetSpeciesActivity::class.java)
                        it.context.startActivity(intent)
                    }
                }
            }
        }
    }

    private fun initRecyclerView(pets: List<PetResposta>) {

        if (pets.isNotEmpty()) {
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.setHasFixedSize(true)
            binding.recyclerView.adapter = PetsAdapter(pets)
        }
    }

    //private fun getPets(idCliente: Int) = viewModel.listPets(idCliente)
    private fun getPets(){
        val sharedPref = requireContext().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        Log.d("CCCCCCC", "${sharedPref.getInt("idCliente", 0)}")
        viewModel.listPets(sharedPref.getInt("idCliente", 0))
    }
}