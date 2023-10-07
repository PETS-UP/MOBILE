package com.petsup.services

import com.petsup.models.Petshop
import retrofit2.Call
import retrofit2.http.GET

interface PetshopService {
    @GET("/petshops")
    fun listPetshops(): Call<List<Petshop>>
}