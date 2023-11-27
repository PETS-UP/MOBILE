package com.petsup.ui.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.petsup.api.Rest
import com.petsup.databinding.ActivityProfileDataBinding
import com.petsup.models.cliente.ClienteDetalhes
import com.petsup.services.ClienteService
import com.petsup.ui.viewmodel.ProfileDataViewModel

class ProfileDataActivity : AppCompatActivity(){
    private lateinit var binding: ActivityProfileDataBinding
    private val viewModel = ProfileDataViewModel()

    private val sharedPref by lazy {
        getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    private var idCliente = 0

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = ActivityProfileDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idCliente = sharedPref.getInt("idCliente", 0)

        setObserver()
        getUserById(idCliente)
    }

    private fun getUserById(idCliente: Int) = viewModel.getUserById(idCliente)

    private fun back() {
        val intent = Intent(this, BottomMenuActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    private fun setObserver() = with(viewModel){
        profileData.observe(this@ProfileDataActivity){
            setContent(it)
        }
    }

    private fun setContent(cliente: ClienteDetalhes) = with(binding){
        nameEditText.text = cliente.nome.toString()
        emailEditText.text = cliente.email.toString()
        phoneEditText.text = cliente.telefone.toString()
        cpfEditText.text = cliente.cpf.toString()
        cepEditText.text = cliente.cep.toString()
        stateEditText.text = cliente.estado.toString()
        cityEditText.text = cliente.cidade.toString()
        districtEditText.text = cliente.bairro.toString()
        streetEditText.text = cliente.rua.toString()
        numberEditText.text = cliente.numero.toString()

        Glide.with(this@ProfileDataActivity).load(sharedPref.getString("imagemPerfilCliente", "")).apply(
            RequestOptions.bitmapTransform(
                CircleCrop()
            )).into(profileIcon)

        arrowBack.setOnClickListener {
            back()
        }
    }
}