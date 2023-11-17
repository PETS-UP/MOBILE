package com.petsup.ui.view.fragment

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.petsup.R
import com.petsup.databinding.FragmentPetListBinding
import com.petsup.models.pet.PetResposta
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
        viewModel.petRespostaViewModel.observe(this) {
            initRecyclerView(it)
        }
    }

    private fun initRecyclerView(petResposta: List<PetResposta>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = PetsAdapter(petResposta)
    }

    //private fun getPets(idCliente: Int) = viewModel.listPets(idCliente)
    private fun getPets(){
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        sharedPref.getString("SerializableUSER", )
        viewModel.listPets()
    }
}