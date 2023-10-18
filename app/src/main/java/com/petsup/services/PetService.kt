package com.petsup.services

import com.petsup.models.pet.PetCadastro
import com.petsup.models.pet.PetResposta
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface PetService {
    @GET("/pets")
    fun listPets(@Query("idCliente") idCliente: Int): Call<List<PetCadastro>>

    @GET("/pets/{idPet}")
    fun getPetById(@Query("idPet") idPet: Int): Call<PetResposta>

    @POST("/adicionar-pilha/{obj}")
    fun addToStack(@Query("obj") obj: String): Call<Unit>

    @GET("/pop-pilha")
    fun popFromStack(): Call<String>

    @POST("/limpa-pilha")
    fun clearStack(): Call<Unit>

    @POST("/cadastrar-pilha")
    fun postPet(): Call<Unit>

    @DELETE("/pets/{idPet}")
    fun deletePet(@Query("idPet") idPet: Int): Call<Unit>

    // obj na query tbm?
    //@RequestParam
    @POST("/clientes/adicionar-pfp/{idPet}/{obj}")
    fun postProfilePicture(@Query("idPet") idPet: Int,
                           @Query("obj") obj: String): Call<Boolean>

    // byte[] == ByteArray ???
    @GET("/clientes/retornar-blob/{idPet}")
    fun getProfilePicture(@Query("idPet") idPet: Int): Call<ByteArray>

    @GET("/clientes/retornar-imagem/{idPet}")
    fun getImage(@Query("idPet") idPet: Int): Call<String>

    @PUT("/clientes/atualizar-imagem/{idPet}")
    fun updateImage(@Query("idPet") idPet: Int): Call<Boolean>

    @DELETE("/clientes/deletar-imagem/{idPet}")
    fun deleteImage(@Query("idPet") idPet: Int): Call<String>
}