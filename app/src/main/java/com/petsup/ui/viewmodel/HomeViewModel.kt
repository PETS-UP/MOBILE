package com.petsup.ui.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petsup.api.Rest
import com.petsup.models.petshop.Petshop
import com.petsup.services.PetshopService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.create


class HomeViewModel (application: Application) : AndroidViewModel(application) {
    private val sharedPref: SharedPreferences = application.getSharedPreferences("SharedPreference", Context.MODE_PRIVATE)
    private var _petshopList = MutableLiveData<List<Petshop>>()
    val petshopList: LiveData<List<Petshop>> = _petshopList

    fun getPetshops() = viewModelScope.launch(Dispatchers.IO) {
        var token: String? = sharedPref.getString("token", "")
        _petshopList.postValue(Rest.getInstance().create<PetshopService>().listPetshops("Bearer $token").execute().body())
    }
}