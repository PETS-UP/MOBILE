package com.petsup.ui.view.activity

import android.content.Context
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
    private lateinit var dateTime: String
    private var idCliente = 0
    private var idServico = 0
    private var nomeServico = ""
    private var precoServico = ""
    private var descricaoServico = ""
    private var idPet = 0
    private var nomePet = ""
    private var sexoPet = ""
    private var especiePet = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setObservers()

        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return

        idCliente = sharedPref.getInt("idCliente", 0)
        idServico = sharedPref.getInt("idServico", 0)
        nomeServico = sharedPref.getString("nomeServico", "").toString()
        precoServico = sharedPref.getString("precoServico", "").toString()
        descricaoServico = sharedPref.getString("descricaoServico", "").toString()
        idPet = sharedPref.getInt("idPet", 0)
        nomePet = sharedPref.getString("nomePet", "").toString()
        sexoPet = sharedPref.getString("sexoPet", "").toString()
        especiePet = sharedPref.getString("especiePet", "").toString()

        petshop = intent.getSerializableExtra("petshop") as Petshop
        dateTime = intent.getStringExtra("datetime")!!

        viewModel.updateViewStateToContent()
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
                    binding.serviceName.text = nomeServico
                    binding.petName.text = nomePet
                    binding.dateName.text = intent.getStringExtra("date")
                    binding.timeName.text = intent.getStringExtra("time")
                    binding.priceName.text = precoServico

                    binding.confirmButton.setOnClickListener {
                        viewModel.postAgendamento(dateTime, idCliente, petshop.id, idPet, idServico)
                    }

                    binding.returnButton.setOnClickListener {
                        val intent = Intent(this, DatetimeSelectionActivity::class.java)
                        intent.putExtra("petshop", petshop)
                        startActivity(intent)
                        this.finish()
                    }

                    binding.arrowBack.setOnClickListener {
                        val intent = Intent(this, DatetimeSelectionActivity::class.java)
                        intent.putExtra("petshop", petshop)
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