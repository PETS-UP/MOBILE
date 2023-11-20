package com.petsup.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.petsup.R
import com.petsup.databinding.FragmentBookingListBinding
import com.petsup.databinding.FragmentHomeBinding
import com.petsup.models.AgendamentoResposta
import com.petsup.models.petshop.Petshop
import com.petsup.ui.view.adapter.BookingsAdapter
import com.petsup.ui.view.adapter.PetshopsAdapter
import com.petsup.ui.viewmodel.BookingListViewModel
import com.petsup.ui.viewmodel.HomeViewModel

class BookingListFragment : Fragment() {

    private lateinit var binding: FragmentBookingListBinding
    private val viewModel = BookingListViewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
        getAgendamentos(2)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookingListBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun setObservers() {
        viewModel.bookingList.observe(viewLifecycleOwner) {
            if (it != null) {
                initRecyclerView(it)
            }
        }
    }

    private fun initRecyclerView(agendamentos: List<AgendamentoResposta>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = BookingsAdapter(agendamentos)
    }

    private fun getAgendamentos(idCliente: Int) = viewModel.getAgendamentos(idCliente)
}