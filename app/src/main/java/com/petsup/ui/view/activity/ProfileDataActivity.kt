package com.petsup.ui.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.petsup.databinding.ActivityProfileDataBinding
import com.petsup.models.cliente.ClienteDetalhes
import com.petsup.ui.viewmodel.ProfileDataViewModel

class ProfileDataActivity : AppCompatActivity(){
    private lateinit var binding: ActivityProfileDataBinding
    private val viewModel = ProfileDataViewModel()

    private var idCliente = 0

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        val sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        idCliente = sharedPref.getInt("idCliente", 0)

        binding = ActivityProfileDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.arrowBack.setOnClickListener {
            back()
        }

        val cliente: ClienteDetalhes = getUserById(idCliente)

        binding.nameEditText.text = cliente.nome.toString()
        binding.emailEditText.text = cliente.email.toString()
        binding.birthDateEditText.text = cliente.dataNasc.toString()
        binding.phoneEditText.text = cliente.telefone.toString()
        binding.cpfEditText.text = cliente.cpf.toString()
        binding.cepEditText.text = cliente.cep.toString()
        binding.stateEditText.text = cliente.estado.toString()
        binding.cityEditText.text = cliente.cidade.toString()
        binding.districtEditText.text = cliente.bairro.toString()
        binding.streetEditText.text = cliente.rua.toString()
        binding.numberEditText.text = cliente.numero.toString()

    }

    private fun getUserById(idCliente: Int) = viewModel.getUserById(idCliente)

    private fun back() {
        val intent = Intent(this, BottomMenuActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}