package com.petsup.services

import com.petsup.models.petshop.Petshop
import com.petsup.models.petshop.PetshopAvaliacao
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface PetshopService {
    @GET("petshops")
    @Headers("accept: application/json")
    fun listPetshops(@Header("Authorization") header:String): Call<List<Petshop>>

    @GET("petshops/{idPetshop}")
    @Headers("accept: application/json")
    fun getPetshopById(@Query("idPetshop") idPetshop: Int,
                       @Header("Authorization") header:String): Call<Petshop>

    @GET("petshops/busca/nome/{obj}")
    @Headers("accept: application/json")
    fun getPetshopsByNome(@Query("obj") obj: String,
                          @Header("Authorization") header:String): Call<List<Petshop>>

    @POST("petshops/inscrever/{idPetshop}")
    @Headers("accept: application/json")
    fun subscribeToPetshop(@Query("idPetshop") idPetshop: Int,
                           @Header("Authorization") header:String): Call<Void>


    @GET("petshops/media-avaliacao")
    @Headers("accept: application/json")
    fun getMediaAvaliacao(@Header("Authorization") header:String): Call<List<PetshopAvaliacao>>
}