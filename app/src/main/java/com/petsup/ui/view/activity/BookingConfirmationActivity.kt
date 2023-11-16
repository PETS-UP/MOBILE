package com.petsup.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.petsup.databinding.ActivityBookingConfirmationBinding
import com.petsup.models.pet.PetResposta
import com.petsup.models.petshop.Petshop
import com.petsup.models.servico.ServicoResposta
import com.petsup.ui.`object`.FormatterObject

class BookingConfirmationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookingConfirmationBinding
    private lateinit var petshop: Petshop
    private lateinit var pet: PetResposta
    private lateinit var servico: ServicoResposta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        petshop = intent.getSerializableExtra("petshop") as Petshop
        pet = intent.getSerializableExtra("pet") as PetResposta
        servico = intent.getSerializableExtra("servico") as ServicoResposta

        binding.petshopName.text = petshop.nome
        binding.serviceName.text = servico.nome
        binding.petName.text = pet.nome
        binding.dateName.text = intent.getStringExtra("date")
        binding.timeName.text = intent.getStringExtra("time")
        binding.priceName.text = FormatterObject.formatServicePrice(servico.preco)
    }
}