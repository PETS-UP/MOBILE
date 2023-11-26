package com.petsup.services

import com.petsup.models.petshop.Petshop
import com.petsup.models.petshop.PetshopAvaliacao
import com.petsup.models.petshop.PetshopMediaAvaliacao
import com.petsup.models.petshop.PetshopMediaPreco
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PetshopService {
    @GET("petshops")
    fun listPetshops(): Call<List<Petshop>>

    @GET("petshops/{idPetshop}")
    fun getPetshopById(@Query("idPetshop") idPetshop: Int): Call<Petshop>

    @GET("petshops/busca/nome/{obj}")
    fun getPetshopsByNome(@Query("obj") obj: String): Call<List<Petshop>>

    @POST("petshops/inscrever/{idPetshop}")
    fun subscribeToPetshop(@Query("idPetshop") idPetshop: Int): Call<Void>

    @GET("clientes/ordenar-media-preco")
    fun getPetshopsOrderByPrecoAsc(): Call<List<PetshopMediaPreco>>

    @GET("clientes/ordenar-media-avaliacao")
    fun getPetshopsOrderByMedia(): Call<List<PetshopMediaAvaliacao>>

    @GET("clientes/petshops-proximos/{idCliente}")
    fun getPetshopsByClienteBairro(@Path("idCliente") idCliente: Int): Call<List<Petshop>>
}