package com.petsup.services

import com.petsup.models.Avaliacao
import com.petsup.models.cliente.ClienteCadastro
import com.petsup.models.cliente.ClienteDetalhes
import com.petsup.models.cliente.ClienteLogin
import com.petsup.models.cliente.ClienteToken
import com.petsup.models.petshop.Petshop
import com.petsup.models.petshop.PetshopAvaliacao
import com.petsup.models.petshop.PetshopMediaPreco
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.reflect.Type

interface
ClienteService {
    @POST("clientes")
    fun postCLiente(@Body signUpRequest: ClienteCadastro): Call<Unit>

    @POST("clientes/login")
    fun login(@Body loginRequest: ClienteLogin): Call<ClienteToken>

    @GET("clientes")
    fun getClientes(): Call<List<ClienteDetalhes>>

    @GET("clientes/{idCliente}")
    fun getClienteById(@Path("idCliente") idCliente: Int): Call<ClienteDetalhes>

    // obj na query tbm?
    //@RequestParam
    @POST("clientes/adicionar-pfp/{idCliente}/{obj}")
    fun postProfilePicture(@Query("idCliente") idCliente: Int,
                           @Query("obj") obj: String): Call<Boolean>

    // byte[] == ByteArray ???
    @GET("clientes/retornar-blob/{idCliente}")
    fun getProfilePicture(@Query("idCliente") idCliente: Int): Call<ByteArray>

    @GET("clientes/retornar-imagem/{idCliente}")
    fun getImage(@Query("idCliente") idCliente: Int): Call<String>

    @PUT("clientes/atualizar-imagem/{idCliente}")
    fun updateImage(@Query("idCliente") idCliente: Int): Call<Boolean>

    @DELETE("clientes/deletar-imagem/{idCliente}")
    fun deleteImage(@Query("idCliente") idCliente: Int): Call<String>

    @GET("clientes/busca-email/{email}")
    fun getUserByEmail(@Path("email") email: String): Call<ClienteDetalhes>

    @PATCH("clientes/{idCliente}")
    fun updateClienteById(@Query("idCliente") idCliente: Int): Call<ClienteDetalhes>

    @POST("clientes/avaliar/{idCliente}/{idPetshop}")
    fun postAvaliacao(
        @Query("idCliente") idCliente: Int,
        @Query("idPetshop") idPetshop: Int
    ): Call<Unit>

    @GET("clientes/avaliacao/{idCliente}/{idPetshop}")
    fun getAvaliacaoCliente(
        @Query("idCliente") idCliente: Int,
        @Query("idPetshop") idPetshop: Int
    ): Call<Avaliacao>

    @GET("clientes/ordenar-media-avaliacao")
    fun getPetshopsOrderByMedia(): Call<List<PetshopAvaliacao>>

    @GET("clientes/ordenar-media-preco")
    fun getPetshopsOrderByPrecoAsc(): Call<List<PetshopMediaPreco>>

    @PATCH("clientes/latitude-longitude/{idCliente}/{latitude}/{longitude}")
    fun updateCurrentLocation(@Query("idCliente") idCliente: Int,
                               @Query("latitude") latitude: Double,
                               @Query("longitude") longitude: Double): Call<Unit>

    @GET("clientes/petshops-proximos/{idCliente}")
    fun getPetshopsByClienteBairro(@Query("idCliente") idCliente: Int): Call<List<Petshop>>
}