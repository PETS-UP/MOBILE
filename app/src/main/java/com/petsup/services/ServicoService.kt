package com.petsup.services

import com.petsup.models.servico.ServicoResposta
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServicoService {

    //@RequestParam
    @GET("servicos")
    fun getServicosByIdPetshop(@Query("idPetshop") idPetshop: Int): Call<List<ServicoResposta>>

    @GET("servicos/{idServico}")
    fun getServicoById(@Query("idServico") idServico: Int): Call<ServicoResposta>
}