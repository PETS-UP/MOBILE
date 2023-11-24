package com.petsup.services

import com.petsup.models.pet.PetResposta
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PetService {
    @GET("pets")
    fun listPets(@Query("idCliente") idCliente: Int): Call<List<PetResposta>>

    @GET("pets/{idPet}")
    fun getPetById(@Path("idPet") idPet: Int): Call<PetResposta>

    @POST("pets/adicionar-pilha/{obj}")
    fun addToStack(@Path("obj") obj: String): Call<Unit>

    @GET("pets/pop-pilha")
    fun popFromStack(): Call<Unit>

    @POST("pets/limpa-pilha")
    fun clearStack(): Call<Unit>

    @POST("pets/cadastrar-pilha")
    fun postPet(@Query("idCliente") idCliente: Int): Call<Unit>

    @DELETE("pets/{idPet}")
    fun deletePet(@Path("idPet") idPet: Int): Call<Unit>
}