package com.petsup.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.petsup.databinding.FragmentPetListBinding
import com.petsup.models.pet.PetCadastro
import com.petsup.ui.view.activity.PetSpeciesActivity
import com.petsup.ui.view.adapter.PetsAdapter
import com.petsup.ui.viewmodel.PetListViewModel

class PetListFragment : Fragment() {

    private lateinit var binding: FragmentPetListBinding
    private val viewModel = PetListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setObservers()
        getPets()

        binding.addPetButton.setOnClickListener {
            val intent = Intent(context, PetSpeciesActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPetListBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun setObservers() {
        viewModel.petCadastroList.observe(this) {
            initRecyclerView(it)
        }
    }

    private fun initRecyclerView(petCadastros: List<PetCadastro>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = PetsAdapter(petCadastros)
    }

    private fun getPets(idCliente: Int) = viewModel.listPets(idCliente)
}