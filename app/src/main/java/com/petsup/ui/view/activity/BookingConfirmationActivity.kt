package com.petsup.ui.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.petsup.databinding.ActivityBookingConfirmationBinding
import com.petsup.models.pet.PetResposta
import com.petsup.models.petshop.Petshop
import com.petsup.models.servico.ServicoResposta
import com.petsup.ui.model.BookingConfirmationViewHolder
import com.petsup.ui.`object`.FormatterObject
import com.petsup.ui.viewmodel.BookingConfirmationViewModel

class BookingConfirmationActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityBookingConfirmationBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        BookingConfirmationViewModel()
    }

    private lateinit var petshop: Petshop
    private lateinit var pet: PetResposta
    private lateinit var servico: ServicoResposta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setObservers()

        petshop = intent.getSerializableExtra("petshop") as Petshop
        pet = intent.getSerializableExtra("pet") as PetResposta
        servico = intent.getSerializableExtra("servico") as ServicoResposta
    }

    private fun setObservers() {
        viewModel.state.observe(this) { view ->
            when (view) {
                is BookingConfirmationViewHolder.Success -> {
                    binding.bookingSuccess.isVisible = true
                    binding.bookingError.isVisible = false
                    binding.bookingContent.isVisible = false

                    binding.successCloseButton.setOnClickListener {
                        val intent = Intent(this, BottomMenuActivity::class.java)
                        startActivity(intent)
                        this.finish()
                    }

                    binding.successReturnButton.setOnClickListener {
                        val intent = Intent(this, BottomMenuActivity::class.java)
                        startActivity(intent)
                        this.finish()
                    }
                }

                is BookingConfirmationViewHolder.Content -> {
                    binding.bookingContent.isVisible = true
                    binding.bookingError.isVisible = false
                    binding.bookingSuccess.isVisible = false

                    binding.petshopName.text = petshop.nome
                    binding.serviceName.text = servico.nome
                    binding.petName.text = pet.nome
                    binding.dateName.text = intent.getStringExtra("date")
                    binding.timeName.text = intent.getStringExtra("time")
                    binding.priceName.text = FormatterObject.formatServicePrice(servico.preco)

                    binding.confirmButton.setOnClickListener {
                        TODO()
                    }

                    binding.returnButton.setOnClickListener {
                        val intent = Intent(this, DatetimeSelectionActivity::class.java)
                        intent.putExtra("petshop", petshop)
                        intent.putExtra("pet", pet)
                        intent.putExtra("servico", servico)
                        startActivity(intent)
                        this.finish()
                    }

                    binding.arrowBack.setOnClickListener {
                        val intent = Intent(this, DatetimeSelectionActivity::class.java)
                        intent.putExtra("petshop", petshop)
                        intent.putExtra("pet", pet)
                        intent.putExtra("servico", servico)
                        startActivity(intent)
                        this.finish()
                    }
                }

                is BookingConfirmationViewHolder.Error -> {
                    binding.bookingError.isVisible = true
                    binding.bookingContent.isVisible = false
                    binding.bookingSuccess.isVisible = false

                    binding.errorCloseButton.setOnClickListener {
                        val intent = Intent(this, BottomMenuActivity::class.java)
                        startActivity(intent)
                        this.finish()
                    }

                    binding.errorReturnButton.setOnClickListener {
                        val intent = Intent(this, BottomMenuActivity::class.java)
                        startActivity(intent)
                        this.finish()
                    }

                    binding.retryButton.setOnClickListener {
                        viewModel.updateViewStateToContent()
                    }
                }
            }
        }
    }
}