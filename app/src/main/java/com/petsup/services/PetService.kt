package com.petsup.services

import com.petsup.models.pet.PetCadastro
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PetService {
    @GET("/pets")
    fun listPets(@Query("idCliente") idCliente: Int): Call<List<PetCadastro>>

    @DELETE("/pets/{idPet}")
    fun deletePet(@Query("idPet") idPet: Int): Call<Unit>

    @POST("/adicionar-pilha/{obj}")
    fun addToStack(@Query("obj") obj: String): Call<Unit>

    @GET("/pop-pilha")
    fun popFromStack(): Call<String>

    @POST("/limpa-pilha")
    fun clearStack(): Call<Unit>

    @POST("/cadastrar-pilha")
    fun postPet(): Call<Unit>
}