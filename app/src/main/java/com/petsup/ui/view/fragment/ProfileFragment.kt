package com.petsup.ui.view.fragment

import android.content.Intent
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.petsup.R
import com.petsup.databinding.FragmentProfileBinding
import com.petsup.ui.view.activity.BottomMenuActivity
import com.petsup.ui.view.activity.ProfileDataActivity
import kotlin.system.exitProcess

class ProfileFragment : Fragment() {
    lateinit var binding : FragmentProfileBinding

    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPrefs = requireContext().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        setOnClick()
        displayUserInfo()

        binding.logoutButton.setOnClickListener {
            val idCliente = sharedPrefs.getInt("idCliente", 0)
            val nomeCliente = sharedPrefs.getString("nomeCliente", "")
            val emailCliente = sharedPrefs.getString("emailCliente", "")
            val imagemPerfilCliente = sharedPrefs.getString("imagemPerfilCliente", "")

            editor.remove("idCliente")
            editor.remove("emailCliente")
            editor.remove("imagemPerfilCliente")
            editor.remove("nomeCliente")
            editor.apply()

            AlertDialog.Builder(requireContext())
                .setMessage("Tem certeza que quer sair do aplicativo?")
                .setPositiveButton("Sair", DialogInterface.OnClickListener { _, _ ->
                    exitProcess(0)
                })
                .setNegativeButton("Cancelar", DialogInterface.OnClickListener { _, _ ->
                    editor.putInt("idCliente", idCliente)
                    editor.putString("nomeCliente", nomeCliente)
                    editor.putString("emailCliente", emailCliente)
                    editor.putString("imagemPerfilCliente", imagemPerfilCliente)
                    editor.apply()
                })
                .create()
                .show()
        }
    }

    private fun setOnClick() = with(binding) {
        editButton.setOnClickListener{
//            it.findNavController().navigate(R.id.action_profile_nav_to_profile_data_nav)
            val intent = Intent(context, ProfileDataActivity::class.java)
            startActivity(intent)
        }
    }

    private fun displayUserInfo() = with(binding) {
        Log.i("IMAGEM PERFIL", sharedPrefs.getString("imagemPerfilCliente", "")!!)
        Log.i("NOME PERFIL", sharedPrefs.getString("nomeCliente", "")!!)
        Log.i("EMAIL PERFIL", sharedPrefs.getString("emailCliente", "")!!)
        Log.i("ID CLIENTE PERFIL", sharedPrefs.getInt("idCliente", 0).toString())
        Glide.with(requireContext()).load(sharedPrefs.getString("imagemPerfilCliente", "")).apply(
            RequestOptions.bitmapTransform(
                CircleCrop()
            )).into(profileIcon)
        profileName.text = sharedPrefs.getString("nomeCliente", "")
    }
}