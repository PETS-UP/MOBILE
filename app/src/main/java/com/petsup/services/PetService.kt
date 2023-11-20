package com.petsup.services

import com.petsup.models.pet.PetCadastro
import com.petsup.models.pet.PetResposta
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PetService {
    @GET("pets")
    fun listPets(@Query("idCliente") idCliente: Int): Call<List<PetResposta>>

    @GET("pets/{idPet}")
    fun getPetById(@Query("idPet") idPet: Int): Call<PetResposta>

    @POST("adicionar-pilha/{obj}")
    fun addToStack(@Path("obj") obj: String): Call<Unit>

    @GET("pop-pilha")
    fun popFromStack(): Call<Unit>

    @POST("limpa-pilha")
    fun clearStack(): Call<Unit>

    @POST("cadastrar-pilha")
    fun postPet(): Call<Unit>

    @DELETE("pets/{idPet}")
    fun deletePet(@Path("idPet") idPet: Int): Call<Unit>
}