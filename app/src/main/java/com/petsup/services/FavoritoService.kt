package com.petsup.services

import com.petsup.models.petshop.Petshop
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FavoritoService {
    @POST("favoritos/{idCliente}/{idPetshop}")
    fun postFavorito(@Path("idCliente") idCliente: Int,
                     @Path("idPetshop") idPetshop: Int): Call<Unit>

    @GET("favoritos/{idCliente}")
    fun getFavoritos(@Path("idCliente") idCliente: Int): Call<List<Petshop>>

    @GET("favoritos/favoritado/{idCliente}/{idPetshop}")
    fun isFavoritado(@Path("idCliente") idCliente: Int,
                     @Path("idPetshop") idPetshop: Int): Call<Boolean>

    @DELETE("favoritos/{idPetshop}")
    fun deleteFavorito(@Path("idCliente") idCliente: Int,
                       @Path("idPetshop") idPetshop: Int): Call<Unit>
}