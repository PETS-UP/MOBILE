package com.petsup.services

import com.petsup.models.Pet
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PetService {
    @GET("/pets")
    fun listPets(@Query("idCliente") idCliente: Int): Call<List<Pet>>

    @DELETE("/pets/{idPet}")
    fun deletePet(@Query("idPet") idPet: Int): Call<Unit>

    @POST("/pets/adicionar-pilha/{obj}")
    fun addToStack(@Query("obj") obj: String): Call<Unit>

    @GET("/pets/pop-pilha")
    fun popFromStack(): Call<String>

    @POST("/pets/limpa-pilha")
    fun clearStack(): Call<Unit>

    @POST("/pets/cadastrar-pilha")
    fun postPet(): Call<Unit>
}